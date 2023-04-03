package com.example.toyproject.dto;

import com.example.toyproject.domain.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardListDto {
    private Member member;  // FK
    private String title;
    private int rate;
    private LocalDateTime createdDate;

    @Builder
    public BoardListDto(Member member, String title, int rate, LocalDateTime createdDate) {
        this.member = member;
        this.title = title;
        this.rate = rate;
        this.createdDate = createdDate;
    }
}
