//package com.example.toyproject.repository;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class BoardRepositoryTest {
//
//    BoardRepository boardRepository = new BoardRepository();
//
//    @AfterEach
//    void afterEach() {
//        boardRepository.clear();
//    }
//
//    @Test
//    public void findByMemberId(){
//
//        boardRepository.save(new Board(1L, "제목 테스트 1-1", "작가 1-1", "내용 1-1", LocalDate.now(), 4, ""));
//        boardRepository.save(new Board(1L, "제목 테스트 1-2", "작가 1-2", "내용 1-2", LocalDate.now(), 5, ""));
//        boardRepository.save(new Board(2L, "제목 테스트 2", "작가 2", "내용 2", LocalDate.now(), 3, ""));
//
//        List<Board> memberBoards = boardRepository.findAll();
//        List<Board> findBoards = new ArrayList<>();
//
//        for (Board memberBoard : memberBoards) {
//            if (memberBoard.getMemberId().equals(1L)) {
//                findBoards.add(memberBoard);
//            }
//        }
//        assertThat(findBoards.size()).isEqualTo(2);
//    }
//}
