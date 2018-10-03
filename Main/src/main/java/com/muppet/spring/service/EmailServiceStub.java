package com.muppet.spring.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.muppet.Email;
import com.muppet.EmailResponse;
import com.muppet.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;


@Service(interfaceClass = EmailService.class)
@Component
public class EmailServiceStub implements EmailService {

    private EmailService service;

    private Logger logger = LogManager.getLogger(this.getClass());

    public EmailServiceStub(EmailService es) {
        this.service = es;
    }

    @Override
    public EmailResponse send(Email email) {
        logger.info("local service invoke");
        return service.send(email);
    }
}
