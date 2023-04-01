package com.example.toyproject.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

//@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@Setter
@NoArgsConstructor//(access = AccessLevel.PROTECTED)
public class BoardV2 {
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private MemberV2 member;
    private String title;
    private String content;
    private String author;
    private int rate;
    private LocalDateTime createdDate;

    // 연관관계 편의 메서드
    public void setMember(MemberV2 member) {
        this.member = member;
        member.getBoards().add(this);
    }


//    @Builder
//    public BoardV2(Long id, String title, String author, String content,
//                   LocalDateTime createdDate, Integer rate, String imageUrl) {
//        this.id = id;
//        this.title = title;
//        this.author = author;
//        this.content = content;
//        this.createdDate = createdDate;
//        this.rate = rate;
//
//    }
}
