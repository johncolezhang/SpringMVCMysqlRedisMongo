package com.johncole.controller;

import com.johncole.entity.Member;
import com.johncole.mongo.MGUserDao;
import com.johncole.service.MGMemberService;
import com.johncole.service.MGUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by johncole on 2017/6/19.
 */
@RequestMapping("/mongo")
@Controller
public class MongoController {
    @Autowired
    private MGUserService userService;

    @Autowired
    private MGMemberService memberService;

    private ModelAndView toView(final String url, Map<String, String> map) {
        ModelAndView view = new ModelAndView(url);
        view.addAllObjects(map);
        return view;
    }

    @RequestMapping(value = {"/save_member"}, method = { RequestMethod.POST, RequestMethod.GET})
    public ModelAndView saveMember(Member member, HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        memberService.save(member);
        map.put("message", "保存成功:" + member.toString());
        return toView("message", map);
    }

    @RequestMapping(value = {"/query_member"}, method = {RequestMethod.GET})
    public ModelAndView queryMember(String id, HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Member member = memberService.queryById(id);
        map.put("message", "Member:" + member.toString());
        return toView("message",map);
    }





}
