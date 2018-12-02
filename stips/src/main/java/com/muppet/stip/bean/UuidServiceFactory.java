package com.muppet.stip.bean;

import com.muppet.service.UuidService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component("uuidService")
public class UuidServiceFactory implements FactoryBean<UuidService> {
    @Override
    public UuidService getObject() throws Exception {
        return new UuidServiceImp();
    }

    @Override
    public Class<?> getObjectType() {
        return UuidService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}

class UuidServiceImp implements UuidService {
    @Override
    public String get() {
        return UUID.randomUUID().toString();
    }
}
