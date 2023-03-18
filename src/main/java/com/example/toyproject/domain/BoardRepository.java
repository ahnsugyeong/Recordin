package com.example.toyproject.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BoardRepository {
    private static final Map<Long, Board> store = new HashMap<>(); //static
    private static long sequence = 0L; //static

    public Board save(Board board) {
        board.setId(++sequence);
        store.put(board.getId(), board);
        return board;
    }

    public Board findById(Long id) {
        return store.get(id);
    }



}
