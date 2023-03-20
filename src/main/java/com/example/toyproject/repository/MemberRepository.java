package com.example.toyproject.repository;

import com.example.toyproject.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberRepository {
    private static final Map<Long, Member> store = new HashMap<>(); //static
    private static long sequence = 0L; //static

    public Member save(Member member) {
        member.setMemberId(++sequence);
        store.put(member.getMemberId(), member);
        return member;
    }

    public Member findByMemberId(Long memberId) {
        return store.get(memberId);
    }

    public Member findByEmail(String email) {   // 추후 JPA로 간단하게 구현 가능
        for (Long memberId : store.keySet()) {
            Member member = store.get(memberId);
            if(member.getEmail().equals(email)) return member;
        }
        return null;
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long memberId, Member updateParam) {
        // 업데이트 전 유효성 검사 필요
        Member findMember = findByMemberId(memberId);
        findMember.setEmail(updateParam.getEmail());
        findMember.setPassword(updateParam.getPassword());
        findMember.setName(updateParam.getName());
    }

    public void clearStore() {
        store.clear();
    }
}
