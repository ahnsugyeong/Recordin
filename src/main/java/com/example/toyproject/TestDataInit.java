package com.example.toyproject;

import com.example.toyproject.domain.Board;
import com.example.toyproject.domain.BoardRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final BoardRepository boardRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        boardRepository.save(new Board("제목 테스트", "내용 테스트", 1L, ""));
        boardRepository.save(new Board("제목 테스트", "내용 테스트", 2L, ""));
    }

}