package com.example.toyproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Date;

//@EntityListeners(AuditingEntityListener.class)
//@Entity
@Getter
@Setter
@NoArgsConstructor//(access = AccessLevel.PROTECTED)
public class Board {

    //@GeneratedValue       -> db에서 직접 할당
    @Id
    private Long boardId;
    private Long memberId;  // FK
    private String title;
    private String writer;
    private String content;
    private LocalDate createdDate;
    private Integer rate;
    private String imageUrl;



    @Builder
    public Board(Long memberId, String title, String writer, String content,
                 LocalDate createdDate, Integer rate, String imageUrl) {
        this.memberId = memberId;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.createdDate = createdDate;
        this.rate = rate;
        this.imageUrl = imageUrl;
    }
}
