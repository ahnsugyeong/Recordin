package com.example.toyproject.repository;

import com.example.toyproject.domain.Member;
import com.example.toyproject.domain.board.Board;
import com.example.toyproject.dto.SignInDto;
import com.example.toyproject.dto.SignUpDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public List<Member> findByEmailAndPassword(String email, String password);
    public List<Board> findByMember(SignInDto signInDto);
 }
