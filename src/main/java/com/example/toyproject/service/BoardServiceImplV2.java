package com.example.toyproject.service;

import com.example.toyproject.domain.Member;
import com.example.toyproject.dto.BoardDto;

import java.util.List;

public class BoardServiceImplV2 implements BoardService{
    @Override
    public List<BoardDto> getBoardList(Member member) {
        return null;
    }

    @Override
    public void postBoard(BoardDto boardDto) {

    }

    @Override
    public BoardDto getBoard(long boardId) {
        return null;
    }

    @Override
    public void updateBoard(BoardDto boardDto) {

    }
}
