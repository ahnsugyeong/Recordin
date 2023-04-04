package com.example.toyproject.dto;

import com.example.toyproject.domain.Member;
import com.example.toyproject.domain.board.Board;
import com.example.toyproject.domain.board.BoardCategory;
import com.example.toyproject.domain.board.Book;
import com.example.toyproject.domain.board.Movie;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MovieDto  extends BoardDto{
    private String director;
    private String imageURL;

    public Board toEntity() {
        Board board = Movie.builder()
                .id(id)
                .member(member)
                .title(title)
                .content(content)
                .rate(rate)
                .createdDate(createdDate)
                .director(director)
                .imageURL(imageURL)
                .dtype(dtype)
                .build();
        return board;
    }

    @Builder
    public MovieDto(Long id, Member member, String title, String content,
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
}
