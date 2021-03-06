package com.muppet.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;


@MapperScan(basePackages = "com.muppet.spring.model.mapper")
//@ImportResource(value = {"classpath:application-context.xml"})
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
public class AppMain {

    public static void main(String[] args) {
        SpringApplication.run(AppMain.class, args);
    }

    //@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
        };
    }

/*
    @Bean()
    public ConsumerConfig providerConfig(ApplicationConfig applicationConfig, RegistryConfig registryConfig) {
        ConsumerConfig providerConfig = new ConsumerConfig(); // 手动注入，不再由spring容器去根据类名 // (com.alibaba.dubbo.config.ApplicationConfig)去获取对应的值
        providerConfig.setApplication(applicationConfig);
        providerConfig.setRegistry(registryConfig);
        return providerConfig;
    }
*/

}
