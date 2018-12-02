package com.muppet.spring.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.muppet.Constants;
import com.muppet.service.ExceptionTestService;

@Service(version = Constants.DEFAULT_VERSION, interfaceClass = ExceptionTestService.class)
public class ExceptionTestServiceImpl implements ExceptionTestService {

    @Override
    public Throwable throwException(Throwable throwable) {
        return throwable;
    }
}
