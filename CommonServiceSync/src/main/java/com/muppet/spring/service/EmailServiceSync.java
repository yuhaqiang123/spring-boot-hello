package com.muppet.spring.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.muppet.Email;
import com.muppet.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
@Service(
        version = "1.0.0")
public class EmailServiceSync implements EmailService {

    Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void send(Email email) {
        logger.error("email message:{}", email.message);
    }
}
