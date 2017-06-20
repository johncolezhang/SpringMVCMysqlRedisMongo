package com.johncole.mongo;

import com.johncole.entity.Member;
import org.springframework.stereotype.Repository;

/**
 * Created by johncole on 2017/6/19.
 */
@Repository
public class MGMemberDao extends MongoGenDao<Member> {
    @Override
    protected Class<Member> getEntityClass() {
        return Member.class;
    }
}
