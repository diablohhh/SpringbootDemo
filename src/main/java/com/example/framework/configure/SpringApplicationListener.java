package com.example.framework.configure;

import com.example.DemoApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author ZhangQiangLong
 * @Date 2020/7/29 11:24
 * @Text: 监听事件
 **/
@Component
public class SpringApplicationListener implements ApplicationListener<ApplicationEvent> {

    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        logger.info("接收到事件:{}",applicationEvent);
        //ApplicationReadyEvent表示完成了初始化，表示应用已经可以接收请求
        if (applicationEvent instanceof ApplicationReadyEvent) {
            logger.info("应用初始化成功，应用可以接受请求:{}",applicationEvent);
        }

    }
}
