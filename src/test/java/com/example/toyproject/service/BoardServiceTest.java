package com.example.toyproject.service;

import com.example.toyproject.domain.Member;
import com.example.toyproject.dto.BoardDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceTest {
    @Autowired
    BoardService boardService;
    @Autowired
    MemberService memberService;


    @Test
    public void postAndGetBoard() {
        // given
        Member member = createMember();
        memberService.signUp(member);

        BoardDto boardDto = createBook(member);


        // when
        Long postBoardId = boardService.postBoard(boardDto);
        BoardDto getBoardDto = boardService.getBoard(postBoardId);

        // then
        assertThat(getBoardDto.getId()).isEqualTo(4L);
    }

    @Test
    public void updateBoard() {
        // given
        Member member = createMember();
        memberService.signUp(member);
        BoardDto boardDto = createBook(member);
        Long postBoardId = boardService.postBoard(boardDto);

        // when
        BoardDto getBoardDto = boardService.getBoard(postBoardId);
        getBoardDto.setTitle("update title");
        boardService.updateBoard(getBoardDto);

        // then
        BoardDto updateBoardDto = boardService.getBoard(getBoardDto.getId());
        assertThat(updateBoardDto.getTitle()).isEqualTo("update title");
    }

    @Test
    public void boardList() {
        // given
        Member member = createMember();
        memberService.signUp(member);
        List<BoardDto> bookList = createBookList(member);
        for (BoardDto boardDto : bookList) {
            boardService.postBoard(boardDto);
        }
        assertThat(boardService.getBoardList(member).size()).isEqualTo(3);
    }

    private BoardDto createBook(Member member) {
        return BoardDto.builder()
                .member(member)
                .title("title")
                .content("content")
                .rate(3)
                .createdDate(LocalDateTime.now())
                .author("author")
                .isbn("1234-1234-1234-1234")
                .dtype("B").build();
    }

    private List<BoardDto> createBookList(Member member) {
        List<BoardDto> list = new ArrayList<>();
        list.add(BoardDto.builder()
                .member(member)
                .title("title1")
                .content("content1")
                .rate(3)
                .createdDate(LocalDateTime.now())
                .author("author")
                .isbn("1234-1234-1234-1234")
                .dtype("B").build());
        list.add(BoardDto.builder()
                .member(member)
                .title("title2")
                .content("content2")
                .rate(3)
                .createdDate(LocalDateTime.now())
                .author("author")
                .isbn("1234-1234-1234-1234")
                .dtype("B").build());
        list.add(BoardDto.builder()
                .member(member)
                .title("title3")
                .content("content3")
                .rate(3)
                .createdDate(LocalDateTime.now())
                .author("author")
                .isbn("1234-1234-1234-1234")
                .dtype("B").build());
        return list;
    }

    private Member createMember() {
        return Member.builder()
                .email("test@gmail.com")
                .password("12345678")
                .name("test name")
                .build();
    }
}
