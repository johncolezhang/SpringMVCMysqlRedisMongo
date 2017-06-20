package com.johncole.entity;

import java.io.Serializable;

/**
 * Created by johncole on 2017/6/16.
 */
public class Member implements Serializable{
    public static final long serialVersionUID = 2L;
    private String id;
    private String nickname;

    public Member() {}

    public Member(String id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
