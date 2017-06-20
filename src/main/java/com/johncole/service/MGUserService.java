package com.johncole.service;

import com.johncole.entity.User;
import com.johncole.mongo.MGUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by johncole on 2017/6/19.
 */
@Service
public class MGUserService {
    @Autowired
    private MGUserDao userDao;

    public void save(User user) {
        userDao.save(user);
    }

    public void delete(User user) {
        userDao.delete(user);
    }

    public User queryById(String id) {
        return userDao.queryById(id);
    }

}
