package com.johncole.service;

import com.johncole.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by johncole on 2017/6/14.
 */

public interface UserService {
    public User findByUsernameAndPwd(String username, String password);

    public List<User> find(User user);

    public void add(User user);

    public void update(User user);

    public void delete(String id);
}
