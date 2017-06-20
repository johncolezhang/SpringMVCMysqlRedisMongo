package com.johncole.controller;

import com.johncole.entity.User;
import com.johncole.service.UserService;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by johncole on 2017/6/14.
 */
@Controller
@RequestMapping("/user")
public class ViewController {
    private Logger logger = Logger.getLogger(ViewController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/find")
    @ResponseBody
    public Map <String, Object> find (User user, HttpServletRequest request) {
        Map <String, Object> map = new HashMap();
        System.out.println("已经进入controller方法");
        logger.info("已经进入controller方法");
        User loginUser = userService.findByUsernameAndPwd(user.getUsername(), user.getPassword());
        if (null != loginUser) {
            map.put("result", "success");
        } else {
            map.put("result", "fail");
        }
        return map;
    }

    @RequestMapping("/success")
    public String success(){
        System.out.println("登录成功。。。。");
        logger.info("登录成功。。。。");
        return "success";
    }
}
