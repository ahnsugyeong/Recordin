package com.example.toyproject.domain.board;

import com.example.toyproject.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@DiscriminatorValue("B")
public class Book extends Board {
    @Column(length = 10, nullable = false)
    private String author;
    private String isbn;

    @Builder
    public Book(Long id, Member member, String title, String content,
                LocalDateTime createdDate, Integer rate, String author, String isbn) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.rate = rate;
        this.author = author;
        this.isbn = isbn;
    }
}
