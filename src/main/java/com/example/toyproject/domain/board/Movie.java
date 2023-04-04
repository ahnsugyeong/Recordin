package com.example.toyproject.domain.board;

import com.example.toyproject.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@DiscriminatorValue("M")
@NoArgsConstructor
public class Movie extends Board {
    @Column(length = 10, nullable = false)
    private String director;
    @Column(length = 10)
    private String imageURL;

    @Builder
    public Movie(Long id, Member member, String title, String content,
                Integer rate, LocalDateTime createdDate, String director, String imageURL, BoardCategory dtype) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.rate = rate;
        this.director = director;
        this.imageURL = imageURL;
        this.dtype = dtype;
    }

    public void updateMovie(String title, String content,
                           Integer rate, String director, String imageURL) {
        this.title = title;
        this.content = content;
        this.rate = rate;
        this.director = director;
        this.imageURL = imageURL;
    }
}
