package com.example.toyproject.service;

import com.example.toyproject.domain.Member;
import com.example.toyproject.domain.board.Board;
import com.example.toyproject.domain.board.Book;
import com.example.toyproject.domain.board.Movie;
import com.example.toyproject.dto.BoardDto;
import com.example.toyproject.dto.MemberInfoDto;
import com.example.toyproject.repository.BoardRepository;
import com.example.toyproject.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

    @Override
    public List<BoardDto> getBoardList(MemberInfoDto memberInfoDto) {
        Member memberByDto = findMemberByDto(memberInfoDto);
        List<Board> boardList = boardRepository.findByMember(memberByDto);
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (Board board : boardList) {
            BoardDto boardDto = EntityToBoardDto(board);
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    @Transactional
    @Override
    public Long postBoard(BoardDto boardDto, MemberInfoDto memberInfoDto) {
        Member memberByDto = findMemberByDto(memberInfoDto);
        boardDto.setMember(memberByDto);
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
    public void updateBoard(BoardDto boardDto, Long boardId) {
        Board board = boardRepository.findById(boardId).get();

        if (boardDto.getDtype().equals("B")) {
            Book book = ((Book) board);
            book.updateBook(
                    boardDto.getTitle(),
                    boardDto.getContent(),
                    boardDto.getRate(),
                    boardDto.getCreatedDate(),
                    boardDto.getAuthor(),
                    boardDto.getIsbn(),
                    boardDto.getDtype()
            );

        }  else {        //else if (boardDto.getDtype().equals("M"))->category 설정 가능해지면 set
            Movie movie = ((Movie) board);
            movie.updateMovie(
                    boardDto.getTitle(),
                    boardDto.getContent(),
                    boardDto.getRate(),
                    boardDto.getCreatedDate(),
                    boardDto.getDirector(),
                    boardDto.getImageURL(),
                    boardDto.getDtype()
            );
        }
    }

    //dto를 통한 mapping 메서드
    private Member findMemberByDto(MemberInfoDto memberInfoDto) {
        List<Member> findMemberList = memberRepository
                .findByEmailAndPassword(memberInfoDto.getEmail(), memberInfoDto.getPassword());
        return findMemberList.stream().findFirst().get();
    }

    private BoardDto EntityToBoardDto(Board board) {
        BoardDto boardDto = null;
        if (board.getDtype().equals("B")) {
            Book book = (Book) board;
            boardDto = BoardDto.builder()
                    .id(book.getId())
                    .member(book.getMember())
                    .title(book.getTitle())
                    .content(book.getContent())
                    .rate(book.getRate())
                    .createdDate(book.getCreatedDate())
                    .author(book.getAuthor())
                    .isbn(book.getIsbn())
                    .dtype(book.getDtype())
                    .build();
        } else {  //category 설정이 가능해지면 조건문 설정 ->board.getDtype().equals("M")
            Movie movie = (Movie) board;
            boardDto = BoardDto.builder()
                    .id(movie.getId())
                    .member(movie.getMember())
                    .title(movie.getTitle())
                    .content(movie.getContent())
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


