package com.muppet.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@EnableSpringConfigured
@Configuration
@ImportResource(value = {"classpath:application-context.xml"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
public class AppMain {

    public static void main(String[] args) {
        SpringApplication.run(AppMain.class, args);
    }
}
