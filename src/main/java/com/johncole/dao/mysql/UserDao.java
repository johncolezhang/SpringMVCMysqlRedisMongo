package com.johncole.dao.mysql;

import com.johncole.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by johncole on 2017/6/14.
 */
@Repository("UserDao")
public interface UserDao {
    public User findByUsernameAndPwd(@Param("username") String name, @Param("password") String password);

    public List<User> find(User User);

    public void add(User User);

    public void update(User User);

    public void delete(String id);
}
