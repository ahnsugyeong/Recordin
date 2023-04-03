package com.example.toyproject.service;

import com.example.toyproject.domain.Member;
import com.example.toyproject.domain.board.Board;
import com.example.toyproject.domain.board.Book;
import com.example.toyproject.dto.BoardDto;
import com.example.toyproject.dto.BoardListDto;
import com.example.toyproject.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImplV2 implements BoardService{

    private BoardRepository boardRepository;

    @Override
    public List<BoardListDto> getBoardList(Member member) {
        List<Board> boardList = boardRepository.findByMember(member);
        List<BoardListDto> boardDtoList = new ArrayList<>();

        for (Board board : boardList) {
            BoardListDto boardDto = EntityToBoardListDto(board);
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    @Override
    public void postBoard(BoardDto boardDto) {
        boardRepository.save(boardDto.toEntity());
    }

    @Override
    public BoardDto getBoard(long id) {
        Board board = boardRepository.findById(id).orElse(null);

        // TODO
    }

    @Override
    public void updateBoard(BoardDto boardDto) {
        // TODO
    }

    // board -> boardDto method
    private BoardListDto EntityToBoardListDto(Board board) {
        BoardListDto boardListDto = BoardListDto.builder()
                .member(board.getMember())
                .title(board.getTitle())
                .rate(board.getRate())
                .createdDate(board.getCreatedDate())
                .build();

        return boardListDto;
    }


