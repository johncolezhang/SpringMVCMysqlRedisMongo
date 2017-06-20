package com.johncole.service;

import com.johncole.entity.Member;
import com.johncole.mongo.MGMemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by johncole on 2017/6/19.
 */
@Service
public class MGMemberService {
    @Autowired
    private MGMemberDao memberDao;

    public void save(Member member) {
        memberDao.save(member);
    }

    public void delete(Member member) {
        memberDao.delete(member);
    }

    public Member queryById(String id) {
        return memberDao.queryById(id);
    }
}
