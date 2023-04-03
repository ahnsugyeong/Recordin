package com.example.toyproject.service;

import com.example.toyproject.domain.Member;
import com.example.toyproject.dto.BoardDto;
import com.example.toyproject.dto.BoardListDto;

import java.util.List;

public interface BoardService {
    List<BoardListDto> getBoardList(Member member);
    void postBoard(BoardDto boardDto);
    BoardDto getBoard(long id);
    void updateBoard(BoardDto boardDto);
}
