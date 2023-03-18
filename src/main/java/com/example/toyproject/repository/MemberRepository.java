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
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long memberId, Member updateParam) {
        // 업데이트 전 유효성 검사 필요
        Member findMember = findById(memberId);
        findMember.setEmail(updateParam.getEmail());
        findMember.setPassword(updateParam.getPassword());
        findMember.setUserName(updateParam.getUserName());
    }

    public void clearStore() {
        store.clear();
    }


}
