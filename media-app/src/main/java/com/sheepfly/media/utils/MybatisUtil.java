package com.sheepfly.media.utils;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * mybatis工具。
 *
 * @author sheepfly
 */
public class MybatisUtil {
    private static final Logger log = LoggerFactory.getLogger(MybatisUtil.class);

    private static SqlSessionFactory sqlSessionFactory;

    public static void init() {
        log.info("初始化工厂");
        InputStream configIS = MybatisUtil.class.getResourceAsStream("/configs/mybatis.xml");
        MybatisSqlSessionFactoryBuilder builder = new MybatisSqlSessionFactoryBuilder();
        sqlSessionFactory = builder.build(configIS);
        log.info("初始化完成");
    }

    public static SqlSession getSqlSession() {
        if (sqlSessionFactory == null) {
            log.warn("没有工厂");
            init();
        }
        return sqlSessionFactory.openSession();
    }

}
