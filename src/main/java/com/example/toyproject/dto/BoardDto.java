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
public abstract class BoardDto {
    protected Long id;
    protected Member member;
    protected String title;
    protected String content;
    protected int rate;
    protected LocalDateTime createdDate;
    protected BoardCategory dtype;
}
