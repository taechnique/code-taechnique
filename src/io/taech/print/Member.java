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

    public int getId() {
        return this.id;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setInfo(Integer id, String nickname) {
        this.id = id;
        this.nickname = nickname;
        this.age = 10;
        this.createTime = LocalDateTime.now();
    }
}
