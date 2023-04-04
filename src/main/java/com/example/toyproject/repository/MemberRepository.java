package com.example.toyproject.repository;

import com.example.toyproject.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public List<Member> findByEmailAndPassword(String email, String password);
}
