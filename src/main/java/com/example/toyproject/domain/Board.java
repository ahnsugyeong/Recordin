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
    private String imgUrl;
    private Date createdDate;

    public Board() {
    }

    public Board(String title, String description, String imgUrl) {
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
    }
}
