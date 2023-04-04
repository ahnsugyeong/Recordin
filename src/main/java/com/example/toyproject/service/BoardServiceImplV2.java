package com.example.toyproject.service;

import com.example.toyproject.domain.Member;
import com.example.toyproject.domain.board.Board;
import com.example.toyproject.domain.board.BoardCategory;
import com.example.toyproject.domain.board.Book;
import com.example.toyproject.domain.board.Movie;
import com.example.toyproject.dto.BoardDto;
import com.example.toyproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BoardServiceImplV2 implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public List<BoardDto> getBoardList(Member member) {
        List<Board> boardList = boardRepository.findByMember(member);
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (Board board : boardList) {
            BoardDto boardDto = EntityToBoardDto(board);
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    @Transactional
    @Override
    public Long postBoard(BoardDto boardDto) {
        Board board = boardDto.toEntity();
        Board savedBoard = boardRepository.save(board);
        return savedBoard.getId();
    }

    @Override
    public BoardDto getBoard(Long id) {
        Board board = boardRepository.findById(id).get();
        BoardDto boardDto = EntityToBoardDto(board);
        return boardDto;
    }

    @Transactional
    @Override
    public void updateBoard(BoardDto boardDto) {
        Board board = boardRepository.findById(boardDto.getId()).get();

        if (boardDto.getDtype() == BoardCategory.BOOK) {
            Book book = ((Book) board);
            book.updateBook(
                    boardDto.getTitle(),
                    boardDto.getContent(),
                    boardDto.getRate(),
                    boardDto.getAuthor(),
                    boardDto.getIsbn()
            );

        } else if (boardDto.getDtype() == BoardCategory.MOVIE) {
            Movie movie = ((Movie) board);
            movie.updateMovie(
                    boardDto.getTitle(),
                    boardDto.getContent(),
                    boardDto.getRate(),
                    boardDto.getDirector(),
                    boardDto.getImageURL()
            );
        }
    }

    private BoardDto EntityToBoardDto(Board board) {
        BoardDto boardDto = null;
        if (board.getDtype() == BoardCategory.BOOK) {
            Book book = (Book) board;
            boardDto = BoardDto.builder()
                    .id(book.getId())
                    .member(book.getMember())
                    .title(book.getTitle())
                    .rate(book.getRate())
                    .createdDate(book.getCreatedDate())
                    .author(book.getAuthor())
                    .isbn(book.getIsbn())
                    .dtype(book.getDtype())
                    .build();
        } else if (board.getDtype() == BoardCategory.MOVIE) {
            Movie movie = (Movie) board;
            boardDto = BoardDto.builder()
                    .id(movie.getId())
                    .member(movie.getMember())
                    .title(movie.getTitle())
                    .rate(movie.getRate())
                    .createdDate(movie.getCreatedDate())
                    .director(movie.getDirector())
                    .imageURL(movie.getImageURL())
                    .dtype(movie.getDtype())
                    .build();
        }

        return boardDto;
    }


}

