package com.example.toyproject.domain.board;

import com.example.toyproject.domain.Member;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@DiscriminatorValue("B")
@NoArgsConstructor
public class Book extends Board {

    @Column(length = 10)
    @Nullable
    private String author;
    @Column(length = 100)
    @Nullable
    private String isbn;

    @Builder
    public Book(Long id, Member member, String title, String content,
                Integer rate, LocalDateTime createdDate, String author, String isbn, String dtype) {
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


    public void updateBook(String title, String content,
                           Integer rate, String author, String isbn) {
        this.title = title;
        this.content = content;
        this.rate = rate;
        this.author = author;
        this.isbn = isbn;
    }
}


