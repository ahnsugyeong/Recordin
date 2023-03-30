package com.example.toyproject.repository;

import com.example.toyproject.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public interface MemberRepository {
    public Member save(Member member);
    public Member findByMemberId(Long memberId);
    public Optional<Member> findByEmail(String email);
    public List<Member> findAll();
    public void update(Long memberId, Member updateParam);
    public void clearStore();
}

