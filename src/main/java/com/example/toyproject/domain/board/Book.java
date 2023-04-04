package com.example.toyproject.domain.board;

import com.example.toyproject.domain.Member;
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
    @Column(length = 10, nullable = false)
    private String author;
    @Column(length = 10)
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
        setMember(member);
    }

    public void updateBook(Long id, Member member, String title, String content,
                           Integer rate, LocalDateTime createdDate, String author, String isbn, String dtype) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.content = content;
        this.rate = rate;
        this.createdDate = createdDate;
        this.author = author;
        this.isbn = isbn;
        this.dtype = dtype;
    }
}


