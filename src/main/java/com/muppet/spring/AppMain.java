package com.muppet.spring;

import com.muppet.spring.model.Hello;
import org.aspectj.lang.Aspects;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;

import java.util.Arrays;


//@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@ImportResource(value = {"classpath:application-context.xml"})
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
}
