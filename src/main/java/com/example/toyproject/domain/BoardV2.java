package com.example.toyproject.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class BoardV2 {
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private MemberV2 member;

    @Column(length = 100, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 10, nullable = false)
    private String author;

    private int rate;
    @LastModifiedDate
    private LocalDateTime createdDate;

    // 연관관계 편의 메서드
    public void setMember(MemberV2 member) {
        this.member = member;
        member.getBoards().add(this);
    }


    @Builder
    public BoardV2(Long id, String title, String author, String content,
                   LocalDateTime createdDate, Integer rate, String imageUrl) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.createdDate = createdDate;
        this.rate = rate;
    }
}
