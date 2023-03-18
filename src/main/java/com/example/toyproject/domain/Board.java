package com.example.toyproject.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
public class Board {
    private Long id;
    private String title;
    private String description;
    private Long userId;
    private String imgUrl;

    private Date createdDate;

    public Board() {
    }

    public Board(String title, String description, Long userId, String imgUrl) {
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.imgUrl = imgUrl;
    }
}
