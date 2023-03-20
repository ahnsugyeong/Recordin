package com.example.toyproject.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter @Setter
public class Board {
    private Long boardId;
    private Long memberId;  // FK
    private String title;
    private String writer;
    private String content;
    private LocalDate createdDate;
    private Integer rate;
    private String imageUrl;



    public Board() {
    }

    public Board(Long memberId, String title, String writer, String content, LocalDate createdDate, Integer rate, String imageUrl) {
        this.memberId = memberId;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.createdDate = createdDate;
        this.rate = rate;
        this.imageUrl = imageUrl;
    }
}
