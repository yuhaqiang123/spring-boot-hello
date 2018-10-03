package com.muppet.spring.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.muppet.Email;
import com.muppet.EmailResponse;
import com.muppet.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
@Service(version = "1.0.0", interfaceClass = EmailService.class)
public class EmailServiceSync implements EmailService {

    Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public EmailResponse send(Email email) {
        logger.info("contex param:{}", RpcContext.getContext().getAttachment("name"));
        logger.error("email message:{}", email.message);
        return new EmailResponse().setSuccess(true).setMsg("dubbo rpc request is success");
    }
}
