package io.taech.print;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

public class Member {

    private int id;
    private String nickname;

    public int getId() {
        return this.id;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setInfo(Integer id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}
