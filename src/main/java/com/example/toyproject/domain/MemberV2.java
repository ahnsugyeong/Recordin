package com.example.toyproject.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
//@EntityListeners(AuditingEntityListener.class)    -> db 연결 이후
@Getter
@Setter
@NoArgsConstructor//(access = AccessLevel.PROTECTED)
public class MemberV2 {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String email;
    private String password;
    private String name;

    @OneToMany(mappedBy = "member")
    private List<BoardV2> boards = new ArrayList<>();


//    @Builder
//    public MemberV2(String email, String password, String name) {
//        this.email = email;
//        this.password = password;
//        this.name = name;
//    }
}

