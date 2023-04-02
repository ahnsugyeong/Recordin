package com.example.toyproject.repository;

import com.example.toyproject.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepository extends JpaRepository<Board, Long> {
}
