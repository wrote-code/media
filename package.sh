# 打包脚本，可以在git-bash或其它linux shell中运行
# 如果你将git安装目录下的usr/bin加入到环境变量PATH中，你也可以直接在cmd中运行sh package.sh dev
# 来打包
# 默认使用coding。

# 必须输入分支名
if [[ $# -ne 1 ]]; then
    log "请输入分支名称";
    exit 1;
fi

branch=$1;
################################################################################

UI_URL="git@e.coding.net:wrote-code/mycode/media-ui.git";
SERVER_URL="git@e.coding.net:wrote-code/mycode/media.git";
WORK_DIR=$(pwd);
UI_DIR=$WORK_DIR/media-ui;
SERVER_DIR=$WORK_DIR/media;
APP_DIR=app;
LOG_FILE=$WORK_DIR/package.log;
# 加了-v参数的rm和mv命令日志写在这里
VERBOSE_LOG_FILE=$WORK_DIR/verbose.log;
MODULE_LIST="common config dataaccess service web";
MODULE_LIB_DIR=$APP_DIR/lib_module;
MAVEN_LIB_DIR=$APP_DIR/lib_maven;
CURRENT_DATE=$(date '+%Y.%m.%d');
CLI_JAR_NAME=cli-$branch;
CLI_JAR=;
APP_JAR_NAME=media-$branch;
APP_JAR=;
JAVA_COMMAND="java -Dspring.profiles.active=prd,cli";
CLASSPATH="lib_module/*;lib_maven/*";
CLI_MAIN_CLASS="LoadSingleFile LoadDirectory";
PACKAGE_NAME=com.sheepfly.media.cli;
#前台版本
REVISION_UI=;
#后台版本
REVISION_SERVER=;
#主版本号
MAIN_VERSION=;
################################################################################
# 预定义函数
# 输出日志，同时在控制台和文件中展示
function log() {
    echo "[$(date +'%Y-%m-%d %H:%M:%S')] $*";
}

# 显示分割线
function startWork() {
    cd $WORK_DIR;
    echo "===============>>>$1<<<===============";
}

function endWork() {
    echo "<<<===============$1===============>>>";
}

function runStatus() {
    log "执行结果 -> $1";
    if [[ $1 -ne 0 ]]; then
         log "执行错误 -> $1";
         exit $1;
    fi
}
################################################################################

echo "=========>>>[$(date +'%Y-%m-%d %H:%M:%S')]<<<=========" >> $LOG_FILE;
echo "=========>>>[$(date +'%Y-%m-%d %H:%M:%S')]<<<=========" >> $VERBOSE_LOG_FILE;

################################################################################
startWork "检查当前工作目录是否正常";
if [ -e $UI_DIR/node_modules ]; then
    log "存在node_modules目录，将该目录移动到父目录";
    mv -v $UI_DIR/node_modules $WORK_DIR;
    runStatus $?
fi

if [[ -e $UI_DIR ]]; then
    log "删除ui目录";
    rm -rfv $UI_DIR >> $VERBOSE_LOG_FILE;
    runStatus $?;
fi
if [[ -e $SERVER_DIR ]]; then
    log "删除server目录";
    rm -rfv $SERVER_DIR >> $VERBOSE_LOG_FILE;
    runStatus $?;
fi

if [[ -e $APP_DIR ]]; then
    log "删除app目录";
    rm -rfv $APP_DIR >> $VERBOSE_LOG_FILE;
    runStatus $?;
fi

endWork "目录检查完成";

log "创建目录";
mkdir $APP_DIR;
mkdir $MODULE_LIB_DIR;
mkdir $MAVEN_LIB_DIR;

################################################################################

################################################################################
startWork "拉取代码";

log "分支名称:$branch";

git clone -b $branch --depth 1 $UI_URL;
runStatus $?;
# 手动复制demo文件
cp -v $UI_DIR/src/pages/demo/index.tsx.backup $UI_DIR/src/pages/demo/index.tsx
runStatus $?;

git clone -b $branch --depth 1 $SERVER_URL;
runStatus $?;

log "创建静态文件目录";
mkdir -p $SERVER_DIR/application/src/main/resources/static;
runStatus $?;

endWork "代码拉取完成";
################################################################################

################################################################################
startWork "设置代码版本并复制发行说明";

log "复制发行说明";
cp -v $SERVER_DIR/releasenote.html $SERVER_DIR/application/src/main/resources/static;
runStatus $?;
cp -v $UI_DIR/releasenote-ui.html $SERVER_DIR/application/src/main/resources/static;
runStatus $?;

log "设置后台代码版本";
cd $SERVER_DIR;
runStatus $?;
REVISION_SERVER=$(git rev-parse --short $branch);
touch "application/src/main/resources/static/server-$REVISION_SERVER";
runStatus $?;
touch "cli/src/main/resources/cli-$REVISION_SERVER";
runStatus $?;

log "设置前台代码版本"
cd $UI_DIR;
runStatus $?;
REVISION_UI=$(git rev-parse --short $branch);
touch "$SERVER_DIR/application/src/main/resources/static/ui-$REVISION_UI";
runStatus $?;

log "设置主版本";
cd $SERVER_DIR;
runStatus $?;
MAIN_VERSION=$(cat main-version);
runStatus $?;
echo "主版本:$MAIN_VERSION";
echo $MAIN_VERSION > "config/src/main/resources/media-application.properties";

endWork "设置完成";
################################################################################

################################################################################
startWork "编译打包";

log "移动前台依赖";
mv -v $WORK_DIR/node_modules $UI_DIR;
runStatus $?;
cd $UI_DIR;
runStatus $?;
# 日志文件不会显示进度条，同时控制台也不会显示进度条
log "编译前台代码";
npm run build >> $LOG_FILE;

if [[ $? -ne 0 ]]; then
    log "前台编译失败";
    # 前台编译失败要将依赖挪出去，否则下次运行会删除依赖
    mv -v $UI_DIR/node_modules $WORK_DIR;
    exit 1;
fi

log "编译完成，挪走node_modules";
mv -v $UI_DIR/node_modules $WORK_DIR;
runStatus $?;

log "编译后台代码";
cd $SERVER_DIR;
runStatus $?;
log "复制前台产物";
cp -rv $UI_DIR/dist/* application/src/main/resources/static >> $VERBOSE_LOG_FILE;
runStatus $?;
log "编译";
mvn -Dfile.encoding=UTF-8 -DskipTests=true package;
runStatus $?;

endWork "编译打包";
################################################################################


################################################################################
startWork "复制依赖包";

log "移动jar包";
APP_JAR=$APP_JAR_NAME-$MAIN_VERSION-$REVISION_SERVER-$REVISION_UI-$CURRENT_DATE.jar;
echo "web程序jar包:$APP_JAR";
CLI_JAR=$CLI_JAR_NAME-$MAIN_VERSION-$REVISION_SERVER-$CURRENT_DATE.jar;
echo "命令行程序jar包:$CLI_JAR";
mv -v $SERVER_DIR/application/target/*.jar $APP_DIR/$APP_JAR;
runStatus $?;
mv -v $SERVER_DIR/cli/target/*.jar $APP_DIR/$CLI_JAR;
runStatus $?;

log "移动模块依赖";
for dir in $MODULE_LIST;do
    mv -v $SERVER_DIR/$dir/target/*.jar $MODULE_LIB_DIR;
done
log "解压jar包";
unzip $APP_DIR/$APP_JAR -d app >> $VERBOSE_LOG_FILE;
runStatus $?;

mv -v $APP_DIR/BOOT-INF/lib/* $MAVEN_LIB_DIR >> $VERBOSE_LOG_FILE;
runStatus $?;
rm -rfv $APP_DIR/BOOT-INF >> $VERBOSE_LOG_FILE;
runStatus $?;
rm -rfv $APP_DIR/META-INF >> $VERBOSE_LOG_FILE;
runStatus $?;
rm -rfv $APP_DIR/org >> $VERBOSE_LOG_FILE;
runStatus $?;
log "删除maven依赖目录中的模块依赖";
for f in $(ls $MODULE_LIB_DIR);do
    rm -v $MAVEN_LIB_DIR/$f;
    runStatus $?;
done

endWork "复制依赖包完成";
################################################################################

################################################################################
startWork "创建启动脚本";
log "创建应用启动脚本";

cd $APP_DIR;
runStatus $?;
echo "$JAVA_COMMAND -jar $APP_JAR" >> start-media.bat;
runStatus $?;
log "创建命令行启动脚本";
for item in $CLI_MAIN_CLASS; do
    echo "$JAVA_COMMAND -Dmodule=$item -cp $CLI_JAR;$CLASSPATH $PACKAGE_NAME.$item %*" >> $item.bat;
    runStatus $?;
done
endWork "启动脚本创建完成";
################################################################################


log "打包完成";
