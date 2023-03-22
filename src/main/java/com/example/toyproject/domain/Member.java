package com.example.toyproject.domain;

import jakarta.validation.constraints.NotBlank;
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

