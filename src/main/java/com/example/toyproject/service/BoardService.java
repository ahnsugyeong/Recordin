package com.example.toyproject.service;

import com.example.toyproject.domain.Member;
import com.example.toyproject.dto.BoardDto;

import java.util.List;

public interface BoardService {
    List<BoardDto> getBoardList(Member member);
    Long postBoard(BoardDto boardDto);
    BoardDto getBoard(Long id);
    void updateBoard(BoardDto boardDto);
}