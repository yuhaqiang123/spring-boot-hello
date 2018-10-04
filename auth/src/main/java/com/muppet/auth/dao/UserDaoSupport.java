package com.muppet.auth.dao;

import com.muppet.auth.model.User;
import com.muppet.data.core.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoSupport extends BaseDaoSupport<User> {

    public UserDaoSupport() {

    }

    @Autowired
    private SessionFactory sessionFactory;


}
	
