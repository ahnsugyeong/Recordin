package com.example.toyproject.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
public class Member {
    private Long memberId;
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 8, max = 15, message = "비밀번호를 8자 이상 15자 이하로 입력해주세요.")
    private String password;
    @NotBlank
    private String name;

    public Member() {

    }

    public Member(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}

