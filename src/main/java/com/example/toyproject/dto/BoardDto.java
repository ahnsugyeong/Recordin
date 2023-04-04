package com.example.toyproject.dto;

import com.example.toyproject.domain.Member;
import com.example.toyproject.domain.board.Board;
import com.example.toyproject.domain.board.Book;
import com.example.toyproject.domain.board.Movie;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private Member member;
    private String title;
    private String content;
    private int rate;
    private LocalDateTime createdDate;
    private String dtype = "B";
    private String author;
    private String isbn;
    private String director;
    private String imageURL;

    public Board toEntity() {
        Board board = null;
        if (dtype.equals("B")) {
            board = Book.builder()
                    .id(id)
                    .member(member)
                    .title(title)
                    .content(content)
                    .rate(rate)
                    .createdDate(createdDate)
                    .author(author)
                    .isbn(isbn)
                    .dtype(dtype)
                    .build();
            return board;
        } else if (dtype.equals("M")) {
            board = Movie.builder()
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
        }
        return board;
    }

    @Builder
    public BoardDto(Long id, Member member, String title, String content,
                    Integer rate, LocalDateTime createdDate, String author, String isbn, String director, String imageURL, String dtype) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.rate = rate;
        this.author = author;
        this.director = director;
        this.imageURL = imageURL;
        this.isbn = isbn;
        this.dtype = dtype;
    }

}
