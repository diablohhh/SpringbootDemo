package com.example;

import com.example.framework.repository.DefaultRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * ComponentScan - 扩大获取bean的范围
 * EntityScan - 实体bean范围
 * EnableJpaRepositories - JPA 扫描范围
 * EnableJpaAuditing - 使用特殊注解
 *
 * @author Diablo
 *  @ComponentScan(basePackages = "com.example")
 */
@SpringBootApplication
@EntityScan(basePackages = "com.example")
@EnableJpaAuditing
@EnableJpaRepositories(
        repositoryBaseClass = DefaultRepositoryImpl.class,
        basePackages = {"com.example"}
)
public class DemoApplication {

    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) throws UnknownHostException {
        ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
        Environment environment = applicationContext.getEnvironment();
        logger.info("\n--------------------------------------------------------\n" +
                        "Application'{}' is Running ! Access URLs :\n" +
                        "Local : http://127.0.0.1:{}\n" +
                        "External:http://{}:{}\n--------------------------------------------------------\n",
                environment.getProperty("spring.application.name"),
                environment.getProperty("server.port"),
                Inet4Address.getLocalHost().getHostAddress(),
                environment.getProperty("server.port")
        );
        /*//logTest
        for(int i = 0 ; i < 3 ; i++){
            logger.info("logback So"+i);
            logger.error("logback Down");
            logger.debug("debug");
        }
        logger.info("logback So44");*/
    }

}
