package com.johncole.controller;

import com.johncole.entity.Member;
import com.johncole.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by johncole on 2017/6/16.
 */
@Controller
@RequestMapping("/redis")
public class MemberController extends BaseMultiController {
    @Autowired
    private MemberService memberService;

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(value = {"/add"}, method = {RequestMethod.GET})
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = new HashMap<String, String>();
        Member member = new Member();
        member.setId("1");
        member.setNickname("johncole");
        memberService.add(member);
        map.put("message", "插入成功");
        return toView("message", map);
    }

    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST})
    public ModelAndView addMember(HttpServletRequest request, HttpServletResponse response,
                                  @ModelAttribute("member")Member member) {
        memberService.add(member);
        Map<String, String> map = new HashMap<String, String>();
        map.put("message", "对象插入成功");
        return toView("message", map);
    }

    //\d 表示0-9 任意一个数字 后面有+号 说明这个0-9单个数位出现一到多次
    @RequestMapping(value = {"/{id:\\d+}/query"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView queryMember(HttpServletRequest request, HttpServletResponse response,
                                    @PathVariable("id")String id) {
        Map<String, String> map = new HashMap<String, String>();
        Member member = memberService.get(id);
        if (null != member) {
            map.put("message","查询到member，id=" + member.getId());
        } else {
            map.put("message","没有查询到member");
        }
        return toView("message", map);
    }

    @RequestMapping(value = {"/{id:\\d+}/delete"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView deleteView(HttpServletRequest request, HttpServletResponse response,
                                   @PathVariable("id")String id) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            memberService.delete(id);
            map.put("message", "id：" + id + "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "id：" + id + "删除失败，" + e.getMessage());
        }
        return toView("message", map);
    }

}
