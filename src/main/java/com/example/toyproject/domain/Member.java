package com.example.toyproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

//@Entity
//@EntityListeners(AuditingEntityListener.class)    -> db 연결 이후
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    //@GeneratedValue   -> db에서 직접 할당
    @Id
    private Long memberId;
    private String email;
    private String password;
    private String name;


    @Builder
    public Member(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}

