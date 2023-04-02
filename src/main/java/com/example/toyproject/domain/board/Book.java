package com.example.toyproject.domain.board;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@DiscriminatorValue("B")
public class Book extends BoardV2{
    private String author;
    private String isbn;
}
