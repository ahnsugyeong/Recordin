package com.example.toyproject.repository;

import com.example.toyproject.domain.Board;
import com.example.toyproject.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardRepository {
    private static final Map<Long, Board> store = new HashMap<>(); //static
    private static long sequence = 0L; //static

    public Board save(Board board) {
        board.setBoardId(++sequence);
        store.put(board.getBoardId(), board);
        return board;
    }

    public void update(Long boardId, Board updateParam) {
        // 업데이트 전 유효성 검사 필요
        Board findBoard = findByBoardId(boardId);
        findBoard.setTitle(updateParam.getTitle());
        findBoard.setWriter(updateParam.getWriter());
        findBoard.setContent(updateParam.getContent());
        findBoard.setCreatedDate(updateParam.getCreatedDate());
        findBoard.setRate(updateParam.getRate());
        findBoard.setImageUrl(updateParam.getImageUrl());
    }

    public Board findByBoardId(Long boardId) {
        return store.get(boardId);
    }

    //추가
    public List<Board> findByMemberId(Long memberId){
        List<Board> memberBoards = new ArrayList<>();
        for (Board board : store.values()) {
            if (board.getMemberId().equals(memberId)) {
                memberBoards.add(board);
            }
        }
        return memberBoards;
    }

    public List<Board> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clear() {
        store.clear();
    }
}
