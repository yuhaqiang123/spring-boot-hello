package com.muppet.auth.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.muppet.Constants;
import com.muppet.service.UuidService;

import java.util.UUID;

@Service(version = Constants.DEFAULT_VERSION)
public class UuidSerivceImpl implements UuidService {
    @Override
    public String get() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
