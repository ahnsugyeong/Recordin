package com.example.toyproject.domain.board;

import com.example.toyproject.domain.Member;
import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public abstract class Board {
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    protected Long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    protected Member member;

    @Column(length = 100, nullable = false)
    protected String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    protected String content;
    protected int rate;
    @LastModifiedDate
    protected LocalDateTime createdDate;

    @Column(name = "dtype", insertable = false, updatable = false)
    protected String dtype;

    // 연관관계 편의 메서드
    public void setMember(Member member) {
        this.member = member;
        member.getBoards().add(this);
    }


}
