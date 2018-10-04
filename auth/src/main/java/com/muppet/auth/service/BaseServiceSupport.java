package com.muppet.auth.service;

import com.muppet.auth.dao.BaseDaoSupport;
import com.muppet.core.ioc.annotation.Autowired;

public class BaseServiceSupport<T> implements BaseService<T> {

    @Autowired
    private BaseDaoSupport<T> baseDaoSupport;

    public boolean add(T t) {
        return baseDaoSupport.add(t);
    }

    public boolean update(T t) {
        return baseDaoSupport.update(t);
    }

    public boolean deletebyId(Object primaryKey) {
        return false;
    }

    public T getById(Object primaryKeyValue) {
        return null;
    }

}
