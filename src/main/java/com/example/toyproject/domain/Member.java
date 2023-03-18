package com.example.toyproject.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
public class Member {
    private Long id;
    private String email;
    private String password;
    private String userName;
    private Date birthDate;
    private List<Board> boards;   // 작성 게시글


    public Member() {

    }

    public Member(String email, String password, String userName, Date birthDate) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.birthDate = birthDate;
    }
}
