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
public class BookDto extends BoardDto {
    private String author;
    private String isbn;

    public Board toEntity() {
        Board board = Book.builder()
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
    }

    @Builder
    public BookDto(Long id, Member member, String title, String content,
                Integer rate, LocalDateTime createdDate, String author, String isbn, BoardCategory dtype) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.rate = rate;
        this.author = author;
        this.isbn = isbn;
        this.dtype = dtype;
    }
}
