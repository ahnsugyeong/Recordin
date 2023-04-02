package com.example.toyproject.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberV2 {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @Column(length = 100, nullable = false)
    private String email;
    @Column(length = 15, nullable = false)
    private String password;

    @Column(length = 10, nullable = false)
    private String name;

    @OneToMany(mappedBy = "member")
    private List<BoardV2> boards = new ArrayList<>();

    @Builder
    public MemberV2(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}

