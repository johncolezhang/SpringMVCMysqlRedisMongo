package com.johncole.service;

import com.johncole.dao.mysql.UserDao;
import com.johncole.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by johncole on 2017/6/14.
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    private StringRedisTemplate redisTemplate;

    @Override
    public User findByUsernameAndPwd(String username, String password) {
        return userDao.findByUsernameAndPwd(username, password);
    }

    @Override
    public List<User> find(User user) {
        return userDao.find(user);
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }
}
