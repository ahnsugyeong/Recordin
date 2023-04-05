package com.example.toyproject;

import com.example.toyproject.domain.Member;
import com.example.toyproject.domain.board.Movie;
import com.example.toyproject.dto.BoardDto;
import com.example.toyproject.dto.SignUpDto;
import com.example.toyproject.repository.MemberRepository;
import com.example.toyproject.service.BoardService;
import com.example.toyproject.service.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestDataInit {

    private final MemberService memberService;
    private final BoardService boardService;
    private final MemberRepository memberRepository;

    /**
     * 테스트용 데이터 추가
     */

    @PostConstruct
    public void init() {
        SignUpDto signUpDto = SignUpDto.builder().email("test@gmail.com").password("12345678").name("test").build();
        Long memberId = memberService.signUp(signUpDto);
        Member member = memberRepository.findById(memberId).get();

        List<BoardDto> bookList = createBookList(member);
        for (BoardDto boardDto : bookList) {
            boardService.postBoard(boardDto);
        }
    }
    private List<BoardDto> createBookList(Member member) {
        List<BoardDto> list = new ArrayList<>();
        list.add(BoardDto.builder()
                .member(member)
                .title("title1")
                .content("content1 test test test")
                .createdDate(LocalDateTime.now())
                .rate(3)
                .author("author1")
                .isbn("1234-1234-1234-1234")
                .dtype("B").build());
        list.add(BoardDto.builder()
                .member(member)
                .title("title2")
                .content("content2 test test test")
                .createdDate(LocalDateTime.now())
                .rate(3)
                .author("author2")
                .isbn("1234-1234-1234-1234")
                .dtype("B").build());
        list.add(BoardDto.builder()
                .member(member)
                .title("title3")
                .content("content3 test test test")
                .createdDate(LocalDateTime.now())
                .rate(3)
                .author("author3")
                .isbn("1234-1234-1234-1234")
                .dtype("B").build());
        return list;
    }

}