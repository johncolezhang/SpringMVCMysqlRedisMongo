package com.johncole.redis;

import com.johncole.entity.Member;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by johncole on 2017/6/16.
 */

public interface MemberDao {
    boolean add(Member member);

    boolean add(List<Member> list);

    void delete(String key);

    void delete(List<String> keys);

    boolean update(Member member);

    Member get(String keyId);
}
