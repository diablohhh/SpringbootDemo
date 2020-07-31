package com.example.framework.configure;

import com.example.framework.repository.DefaultRepositoryImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author ZhangQiangLong
 * @Date 2020/7/6 17:55
 * @Text:
 **/
/*@Configuration
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware",dateTimeProviderRef = "springSecurityAuditorAware")
@EnableJpaRepositories(
        repositoryBaseClass = DefaultRepositoryImpl.class,
        basePackages = {"com.example.**.**.repository"}
)
@EntityScan("com.example.*")*/
public class DefaultRepositoryConfig {
}
