package com.example.toyproject.domain.board;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@DiscriminatorValue("M")
public class Movie extends BoardV2{
    private String director;
    private String imageURL;
}
