package com.example.toyproject;

import com.example.toyproject.domain.Member;
import com.example.toyproject.dto.BoardDto;
import com.example.toyproject.dto.MemberInfoDto;
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
        //Member member = Member.builder().email("test@gmail.com").password("12345678").name("test").build();
        SignUpDto signUpDto = new SignUpDto("test@gmail.com", "12345678", "test");
        memberService.signUp(signUpDto);
        //아래 줄부터 error 발생
        List<Member> members = memberRepository.findByEmailAndPassword("test@gmail.com", "12345678");
        Member member = members.stream().findFirst().get();
        MemberInfoDto memberInfoDto = MemberInfoDto.builder()
                .email(member.getEmail())
                .password(member.getPassword())
                .name(member.getName()).build();

        List<BoardDto> bookList = createBookList();

        for (BoardDto boardDto : bookList) {
            Long postBoardId = boardService.postBoard(boardDto, memberInfoDto);
        }


    }
    private List<BoardDto> createBookList() {
        List<BoardDto> list = new ArrayList<>();
        list.add(BoardDto.builder()
                .title("title1")
                .content("content1 test test test")
                .rate(3)
                .createdDate(LocalDateTime.now())
                .author("author1")
                .isbn("1234-1234-1234-1234")
                .dtype("B").build());
        list.add(BoardDto.builder()
                .title("title2")
                .content("content2 test test test")
                .rate(3)
                .createdDate(LocalDateTime.now())
                .author("author2")
                .isbn("1234-1234-1234-1234")
                .dtype("B").build());
        list.add(BoardDto.builder()
                .title("title3")
                .content("content3 test test test")
                .rate(3)
                .createdDate(LocalDateTime.now())
                .author("author3")
                .isbn("1234-1234-1234-1234")
                .dtype("B").build());
        return list;
    }

}