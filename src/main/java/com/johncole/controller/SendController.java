package com.johncole.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.johncole.entity.User;
import com.johncole.service.UserService;
import com.johncole.util.Base64Util;
import com.johncole.util.MsgUtil;
import com.johncole.util.mailUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by johncole on 2017/6/22.
 */
@RequestMapping("/send")
@Controller
public class SendController {
    @Autowired
    public UserService userService;

    @RequestMapping("/open")
    public String openEmail(HttpServletRequest request) {
        return "send";
    }

    @RequestMapping(value = "/cell_send", method = {RequestMethod.POST})
    public String sendMsg(@RequestParam("cell") String cell, HttpServletRequest request, ModelMap modelMap) {
        User user = userService.findByUsername(cell);//查找用户
        if (null == user) {
            user = new User(cell);
            userService.add(user);
        }//不存在则新建一个用户

        if(null != user.getIs_validate() && user.getIs_validate().equals("true")) {
            modelMap.put("message", "用户" + user.getUsername() + "已经注册!");
            return "message";
        }

        String secret_key = (int)(((Math.random() * 9) + 1) * 10000) + "";//生成5位数字密钥
        String cur_timestamp = new Timestamp(System.currentTimeMillis() + 30 * 60 * 1000).toString();//注册有效期为30分钟
        user.setValidate_code(secret_key);//生成一个uuid作为密钥
        user.setIs_validate("false");
        user.setRegister_time(cur_timestamp);//生成时间戳
        userService.update(user);

        //发短信
        try {
            SendSmsResponse response = MsgUtil.sendSms(cell, secret_key, "ss");
            System.out.println("短信接口返回的数据----------------");
            System.out.println("Code=" + response.getCode());
            System.out.println("Message=" + response.getMessage());
            System.out.println("RequestId=" + response.getRequestId());
            System.out.println("BizId=" + response.getBizId());

            if(!response.getCode().equals("OK")) {
                modelMap.put("message", "发送失败,code:" + response.getCode() + " message:" + response.getMessage());
                return "message";
            }
        } catch (ClientException e) {
            e.printStackTrace();
            System.out.println("发送失败");
        }


        modelMap.put("cell", cell);
        return "auth_cell";
    }

    @RequestMapping(value = {"/cell_auth"}, method = {RequestMethod.POST})
    public String authMsg(@RequestParam("auth_code") String auth_code, @RequestParam("cell") String cell, HttpServletRequest request, ModelMap modelMap) {
        User user = userService.findByUsername(cell);
        if (null == user) {
            modelMap.put("message", "没有该用户:" + cell);
            return "message";
        }

        if (! auth_code.equals(user.getValidate_code())) {
            modelMap.put("message", "该用户:" + auth_code + "的validation_code匹配失败");
            return "message";
        }

        Timestamp now_ts = new Timestamp(System.currentTimeMillis());
        Timestamp limit_ts = Timestamp.valueOf(user.getRegister_time());

        if (now_ts.getTime() > limit_ts.getTime()) {//超时
            modelMap.put("message", "该用户:" + cell + "的的验证超时");
            return "message";
        }

        user.setIs_validate("true");
        userService.update(user);
        modelMap.put("message", "该用户:" + cell + "的的验证成功");
        return "message";
    }

    @RequestMapping(value = {"/email_send"}, method = {RequestMethod.POST})
    public String sendEmail(@RequestParam("email") String email, HttpServletRequest request, ModelMap modelMap) {
        User user = userService.findByUsername(email);//查找用户
        if (null == user) {
            user = new User(email);
            userService.add(user);
        }//不存在则新建一个用户

        /******************         产生加密的str             **************************/
        String secret_key = UUID.randomUUID().toString();
        String cur_timestamp = new Timestamp(System.currentTimeMillis() + 30 * 60 * 1000).toString();//注册有效期为30分钟
        user.setValidate_code(secret_key);//生成一个uuid作为密钥
        user.setIs_validate("false");
        user.setRegister_time(cur_timestamp);//生成时间戳
        userService.update(user);
        String auth_code = Base64Util.encodeBase64(email + "$" + secret_key + "$" + cur_timestamp);

        JavaMailSender sender = mailUtil.initMailSender();
        try {
            mailUtil.sendEmail(sender, email, auth_code);
        } catch (MessagingException e) {
            System.out.println("发送失败");
            e.printStackTrace();
        }
        System.out.println("邮件发送成功");
        modelMap.put("message", "用户：" + email + "，请查看邮件并点击链接");
        return "message";
    }

    @RequestMapping(value = {"/email_auth"}, method = {RequestMethod.GET})
    public String authEmail(String auth_code, HttpServletRequest request, ModelMap modelMap) {
        String decode = Base64Util.decodeBase64(auth_code);
        String[] infos = decode.split("\\$");//0为用户名，1为validation_code，2为时间戳
        User user = userService.findByUsername(infos[0]);
        if (null == user) {
            modelMap.put("message", "没有该用户:" + infos[0]);
            return "message";
        }

        if (! infos[1].equals(user.getValidate_code())) {
            modelMap.put("message", "该用户:" + infos[0] + "的validation_code匹配失败");
            return "message";
        }

        Timestamp limit_ts = Timestamp.valueOf(infos[2]);
        Timestamp now_ts = new Timestamp(System.currentTimeMillis());

        if (now_ts.getTime() > limit_ts.getTime()) {//超时
            modelMap.put("message", "该用户:" + infos[0] + "的的验证超时");
            return "message";
        }

        user.setIs_validate("true");
        userService.update(user);
        modelMap.put("message", "该用户:" + infos[0] + "的的验证成功");
        return "message";
    }
}
