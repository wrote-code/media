package com.sheepfly.media.common.exception;

/**
 * 系统错误码。
 *
 * <p>错误码一共8位。第一位是26个大写字母，用来表示错误来源。第二位表示操作结果，1表示成功，0表示失
 * 败。3-5位是模块代码，从100开始递增。5-8位是错误序号，从1开始递增。模块代码不区分子模块和父模块，
 * 也就是说，若存在以下模块代码：101，102，121，122，则这四个模块不一定分别属于两个父模块，他们可
 * 能都是父模块，也可能是四个子模块，也可能其中一个是父模块，另外三个是这个父模块的子模块。在定义
 * 变量时，为了避免出现一行代码半行变量，应该尽量使用简明扼要的缩写。</p>
 *
 * <p>错误码有以下特殊情况。</p>
 * <ol>
 * <li>8位错误码都是0：表示操作成功。</li>
 * <li>错误码3-5位小于200，表示和模块无关的通用错误。200开始表示业务错误。</li>
 * <li>E0999999：表示未知错误，此错误码由全局异常处理器抛出。</li>
 * </ol>
 *
 * <p>模块代码如下：</p>
 * <ul>
 * <li>100:用户未登录</li>
 * <li>101:公用错误</li>
 * <li>103:系统异常</li>
 * </ul>
 *
 * @author wrote-code
 * @since 0.0.1-SNAPSHOT
 */

public enum ErrorCode {
    /*
     ***************************************************************************
     * 模块[module][prefix]开始[999]
     */
    // 正文。新模块补在===前面
    /*
     * 模块[module][prefix]结束[999]
     ***************************************************************************
     */


    /**
     * 操作成功。
     */
    OPERATION_SUCCESS("E0000000", "操作成功"),
    /**
     * 用户未登录。
     */
    USER_NOT_LOG_IN("E0100001", "用户未登录"),

    /*
     ***************************************************************************
     * 网站模块开始201
     */
    /**
     * 网站名称和网站地址不能为空。
     */
    SITE_NAME_URL_CANT_BE_NULL("E0200001", "网站名称和网站地址不能为空"),
    /**
     * 网站地址不合法。
     */
    URL_IS_ILLEGAL("E0200002", "网站地址不合法"),
    SITE_ID_CANT_NULL("E0200003", "网站标识不能为空"),
    SITE_CANT_BE_DELETE("E0200004", "网站下有作者，不能删除网站"),
    /*
     * 网站模块结束
     ***************************************************************************
     */

    /*
     ***************************************************************************
     * 作者模块开始201
     */
    /**
     * 注册网站为空。
     */
    AUTHOR_SITE_CANT_BE_NULL("E0201001", "注册网站不能为空"),
    /**
     * 用户名和用户id不能同时为空。
     */
    AUTHOR_ID_AND_NAME_CANT_NULL("E0201002", "用户名和用户id不能同时为空"),
    /**
     * 作者标识不能为空。
     */
    AUTHOR_ID_CANT_BE_NULL("E0201002", "作者标识不能为空"),
    AUTHOR_ASSOCIATE_RESOURCE("E0201003", "作者下由关联的资源"),
    /*
     * 作者模块结束
     ***************************************************************************
     */

    /*
     ***************************************************************************
     * 模块[资源][res]开始[202]
     */
    RESOURCE_MKDIR_FAIL("E0202001", "创建目录失败，请重试"),
    RES_ADD_FAIL_BY_DUPLICATED("E0202002", "资源重复，添加失败"),
    RES_TAG_NAME_CANT_NULL("E0202003", "标签名称不能为空"),
    RES_TAG_NOT_FOUND("E0202004", "标签不存在"),
    RES_DONT_HAVE_THIS_TAG("E0202005", "指定资源没有设置此标签"),
    /*
     * 模块[资源][res]结束[203]
     ***************************************************************************
     */

    /*
     ***************************************************************************
     * 模块[目录][DIRECTORY]开始[203]
     */
    DIRECTORY_MUST_BE_ABSOLUTE("E0203001", "目录格式有错误，必须是绝对路径"),
    DIRECTORY_ILLEGAL_DRIVER("E0203002", "目录格式错误，不正确的Windows盘符"),
    DIRECTORY_EMPTY_ROOT_DIRECTORY("E0203003", "根目录不存在，请检查数据库"),
    /*
     * 模块[目录][DIRECTORY]结束[203]
     ***************************************************************************
     */
    /*
     ***************************************************************************
     * 模块[标签][tag]开始[204]
     */
    TAG_NAME_CANT_BE_EMPTY("E0204001", "标签名不能为空"),
    TAG_NAME_TOO_LONG("E0204001", "标签名太长"),
    TAG_RES_ID_CANT_BE_NULL("E0204002", "缺少资源标识"),
    TAG_RES_ID_AND_TAG_ID_CANT_BE_NULL("E0204003", "资源标识或标签标识为空"),
    /*
     * 模块[标签][tag]结束[204]
     ***************************************************************************
     */
    // ==========分界线

    /*
     **************************************************************************
     * 公共模块开始101
     */
    /**
     * 要删除的数据不存在。
     */
    DELETE_NOT_EXIST_DATA("E0101001", "要删除的数据不存在"),
    /**
     * 重复的数据。
     */
    SAVE_DUPLICATED_DATA("E0101002", "重复的数据"),
    /**
     * 覆盖文件。
     */
    REWRITE_FILE("E0101003", "覆盖文件"),
    /**
     * 文件不存在。
     */
    FILE_NOT_FOUND("E0101004", "文件不存在"),
    /**
     * 转换失败。
     */
    DATA_TO_FORM_FAIL("E0101005", "转换失败"),
    /**
     * 缺少请求参数。
     */
    REQUEST_VALUE_IS_LOST("E0101006", "缺少请求参数"),
    PAGINATION_ERROR("E0101007", "缺少分页参数"),
    /**
     * 逻辑删除失败：调用反射创建实例失败。
     */
    LOGIC_DELETE_CREATE_FAIL("E0103001", "逻辑删除失败：创建实例失败"),
    LOGIC_DELETE_NOT_SUPPORT("E0103002", "目标实例不支持逻辑删除"),
    /**
     * 未知错误，请联系管理员。
     */
    UNEXPECT_ERROR("E0999999", "未知错误，请联系管理员"),
    VALIDATE_ERROR("E0999998", "输入参数有误");
    /* 公共模块结束
     **************************************************************************
     */

    private final String code;
    private final String message;

    ErrorCode(String errorCode, String message) {
        this.code = errorCode;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
