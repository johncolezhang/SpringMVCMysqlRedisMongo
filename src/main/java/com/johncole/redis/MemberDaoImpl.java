package com.johncole.redis;

import com.johncole.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by johncole on 2017/6/16.
 */
@Component
public class MemberDaoImpl implements MemberDao {
    @Resource
    public RedisTemplate<String, Member> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, Member> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public RedisSerializer<String> getRedisSerializer() {
        return redisTemplate.getStringSerializer();
    }

    @Override
    public boolean add(final Member member) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte []key = serializer.serialize(member.getId());
                byte []name = serializer.serialize(member.getNickname());
                return redisConnection.setNX(key, name);
            }
        });//进行序列化后加入redis中
        return result;
    }

    @Override
    public boolean add(final List<Member> list) {
        Assert.notEmpty(list);
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                for (Member member : list) {
                    byte []key = serializer.serialize(member.getId());
                    byte []name = serializer.serialize(member.getNickname());
                    return redisConnection.setNX(key, name);
                }
                return true;
            }
        },false,true);
        return result;
    }

    @Override
    public void delete(String key) {
    List<String> list = new ArrayList<String>();
    list.add(key);
    delete(list);
    }

    @Override
    public void delete(List<String> keys) {
        redisTemplate.delete(keys);
    }

    @Override
    public boolean update(final Member member) {
        String key = member.getId();
        if (null == get(key)) {
            throw new  NullPointerException("数据不存在，key=" + key);
        }
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    RedisSerializer<String> serializer = getRedisSerializer();
                    byte []key = serializer.serialize(member.getId());
                    byte []name = serializer.serialize(member.getNickname());
                    redisConnection.set(key, name);
                    return true;
            }
        });
        return result;
    }

    @Override
    public Member get(final String keyId) {
        Member result = redisTemplate.execute(new RedisCallback<Member>() {
            @Override
            public Member doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte []key = serializer.serialize(keyId);
                byte []value = redisConnection.get(key);
                if (null == value) {
                    return null;
                }
                String nickname = serializer.deserialize(value);
                return new Member(keyId, nickname);
            }
        });
        return result;
    }
}
