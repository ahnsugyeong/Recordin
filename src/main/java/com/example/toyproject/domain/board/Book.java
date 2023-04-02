package com.example.toyproject.domain.board;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("B")
public class Book extends Board {
    @Column(length = 10, nullable = false)
    private String author;
    private String isbn;
}
