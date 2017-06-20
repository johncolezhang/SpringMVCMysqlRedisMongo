package com.johncole.mongo;

/**
 * Created by johncole on 2017/6/19.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public abstract class MongoGenDao<T> {
    @Autowired
    protected MongoTemplate mongoTemplate;


    protected abstract Class<T> getEntityClass();

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void save(T t) {
        //默认以实体对象名全部小写作为collection名
        mongoTemplate.save(t);

    }

    public T queryById(String id) {
        Query query = new Query();
        Criteria criteria = Criteria.where("_id").is(id);
        query.addCriteria(criteria);
        return mongoTemplate.findOne(query, getEntityClass());
    }

    public List<T> queryList(Query query) {
        return mongoTemplate.find(query, getEntityClass());
    }

    public List<T> getPage(Query query, int start, int size) {
        query.skip(start);
        query.limit(size);
        return mongoTemplate.find(query, getEntityClass());
    }

    public Long getCount(Query query) {
        return mongoTemplate.count(query, getEntityClass());
    }

    public void delete(T t) {
        mongoTemplate.remove(t);
    }

    public void updateMulti(Query query, Update update) {
        mongoTemplate.updateMulti(query, update, getEntityClass());
    }
    public void updateInsert(Query query, Update update) {
        mongoTemplate.upsert(query, update, getEntityClass());
    }

}
