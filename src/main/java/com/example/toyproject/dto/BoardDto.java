package com.example.toyproject.dto;

import com.example.toyproject.domain.Member;
import com.example.toyproject.domain.board.Board;
import com.example.toyproject.domain.board.BoardCategory;
import com.example.toyproject.domain.board.Book;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private Member member;  // FK
    private String title;
    private String content;
    private int rate;
    private LocalDateTime createdDate;

    // Book
    private String author;
    private String isbn;

    // Movie
    private String director;
    private String imageURL;

    private BoardCategory dtype;


    public Board toEntity() {
        Board board = null;
        if (dtype == BoardCategory.BOOK) {
            board = Book.builder()
                    .id(id)
                    .member(member)
                    .title(title)
                    .content(content)
                    .rate(rate)
                    .createdDate(createdDate)
                    .author(author)
                    .isbn(isbn).build();
        } else if (dtype == BoardCategory.MOVIE) {
            // TODO
        }

        return board;
    }

    @Builder
    public BoardDto(Long id, Member member, String title, String content, int rate, LocalDateTime createdDate, String author, String isbn, String director, String imageURL, BoardCategory dtype) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.content = content;
        this.rate = rate;
        this.createdDate = createdDate;
        this.author = author;
        this.isbn = isbn;
        this.director = director;
        this.imageURL = imageURL;
        this.dtype = dtype;
    }
}
