package com.johncole.mongo;

import com.johncole.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by johncole on 2017/6/19.
 */
@Repository
public class MGUserDao extends MongoGenDao<User>{
    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
