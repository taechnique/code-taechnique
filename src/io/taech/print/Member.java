package io.taech.print;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

public class Member {

    private int id;
    private String nickname;
    private int age;
    private LocalDateTime createTime;

    protected Member() {}
    public int getId() {
        return this.id;
    }

    public String getNickname() {
        return this.nickname;
    }

    public static Member create(Integer id, String nickname) {
        Member member = new Member();
        member.id = id;
        member.nickname = nickname;
        member.age = 10;
        member.createTime = null;
        return member;
    }
}
