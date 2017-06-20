package com.johncole.service;

import com.johncole.redis.MemberDao;
import com.johncole.entity.Member;
import com.johncole.redis.MemberDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by johncole on 2017/6/16.
 */
@Service
public class MemberService {
    @Autowired
    private MemberDaoImpl memberDao;

    public void setMemberDao(MemberDaoImpl memberDao) {
        this.memberDao = memberDao;
    }

    public boolean add(Member member) {
        return memberDao.add(member);
    }

    public boolean add(List<Member> members) {
        return memberDao.add(members);
    }

    public void delete(String id) {
        memberDao.delete(id);
    }

    public Member get(String id) {
        return memberDao.get(id);
    }

    public boolean update(Member member) {
        return memberDao.update(member);
    }
}
