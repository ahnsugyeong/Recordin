package com.example.toyproject.domain.board;

import com.example.toyproject.domain.Member;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public abstract class Board {
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(length = 100, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    private int rate;
    @LastModifiedDate
    private LocalDateTime createdDate;

    // 연관관계 편의 메서드
    public void setMember(Member member) {
        this.member = member;
        member.getBoards().add(this);
    }


//    @Builder
//    public Board(Long id, Member member, String title, String content,
//                 LocalDateTime createdDate, Integer rate) {
//        this.id = id;
//        this.member = member;
//        this.title = title;
//        this.content = content;
//        this.createdDate = createdDate;
//        this.rate = rate;
//    }
}
