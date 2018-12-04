package com.muppet.spring;

import com.muppet.spring.model.Hello;
import com.muppet.spring.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.test.context.junit4.SpringRunner;

@EnableAspectJAutoProxy
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableSpringConfigured
public class Test {

    Logger logger = LogManager.getLogger(this.getClass());

    @org.junit.Test
    public void test() {
        logger.error("hello test");
    }
}

