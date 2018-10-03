package com.muppet.spring.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.service.GenericException;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.muppet.EmailService;
import org.springframework.stereotype.Component;

@Component
@Service(version = "v1.0.0", interfaceClass = GenericService.class)
public class Emai2Service extends EmailServiceSync implements EmailService, GenericService {
    public Object $invoke(String methodName, String[] parameterTypes, Object[] args) throws GenericException {
        return "hi generic";
    }
}
