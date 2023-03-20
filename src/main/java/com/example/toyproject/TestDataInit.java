package com.example.toyproject;

import com.example.toyproject.domain.Board;
import com.example.toyproject.domain.Member;
import com.example.toyproject.repository.BoardRepository;
import com.example.toyproject.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        memberRepository.save(new Member("hello@gmail.com", "123", "hello"));
        memberRepository.save(new Member("hello2@gmail.com", "123", "hello2"));

        boardRepository.save(new Board(1L, "제목 테스트 1", "작가 1", "내용 1", LocalDate.now(), 4, ""));
        boardRepository.save(new Board(1L, "제목 테스트 2", "작가 2", "내용 2", LocalDate.now(), 3, ""));

    }

}