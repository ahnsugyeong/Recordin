package com.example.toyproject.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Member {
    private Long memberId;
    private String email;
    private String password;
    private String name;

    public Member() {

    }

    public Member(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
