package com.johncole.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by johncole on 2017/6/14.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String username;
    private String password;
    private String register_time;
    private String is_validate;
    private String sex;
    private String validate_code;

    public User () {}

    public User (String username) {
        this.username = username;
    }

    public User(String id, String username, String password, String sex,
    String register_time, String is_validate, String validate_code) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.register_time = register_time;
        this.is_validate = is_validate;
        this.validate_code = validate_code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRegister_time() {
        return register_time;
    }

    public void setRegister_time(String register_time) {
        this.register_time = register_time;
    }

    public String getIs_validate() {
        return is_validate;
    }

    public void setIs_validate(String is_validate) {
        this.is_validate = is_validate;
    }

    public String getValidate_code() {
        return validate_code;
    }

    public void setValidate_code(String validate_code) {
        this.validate_code = validate_code;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", register_time='" + register_time + '\'' +
                ", is_validate='" + is_validate + '\'' +
                ", sex='" + sex + '\'' +
                ", validate_code='" + validate_code + '\'' +
                '}';
    }
}
