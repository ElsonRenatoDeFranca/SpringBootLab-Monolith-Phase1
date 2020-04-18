package com.company.bankingaccount.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication(exclude={MongoAutoConfiguration.class})
@EntityScan("com.company.bankingaccount.entity")
@EnableJpaRepositories("com.company.bankingaccount.dao")
@ComponentScan({"com.company.bankingaccount.converter",
        "com.company.bankingaccount.app",
        "com.company.bankingaccount.controller",
        "com.company.bankingaccount.service",
        "com.company.bankingaccount.service.impl",
        "com.company.bankingaccount.exception", "com.company.bankingaccount.util"})
@EnableCaching
@EnableSwagger2
public class BankAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountApplication.class, args);
    }

}
