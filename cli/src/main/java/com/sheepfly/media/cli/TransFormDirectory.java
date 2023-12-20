package com.sheepfly.media.cli;

import com.sheepfly.media.cli.task.impl.TransFormDirectoryTaskImpl;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.sheepfly.media")
@EnableJpaRepositories(basePackages = "com.sheepfly.media.dataaccess.repository")
@MapperScan("com.sheepfly.media.dataaccess.mapper")
@ImportResource("classpath:configs/springboot.xml")
@Slf4j
public class TransFormDirectory {
    public static void main(String[] args) throws Exception {
        log.info("转换目录");
        log.info("初始化spring上下文");
        SpringApplication springApplication = new SpringApplication(TransFormDirectory.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        ConfigurableApplicationContext context = springApplication.run(args);
        log.info("初始化完成");
        TransFormDirectoryTaskImpl task = context.getBean(TransFormDirectoryTaskImpl.class);
        task.initializeTaskConfig();
        task.executeTask();
        task.afterTaskFinish();
    }
}