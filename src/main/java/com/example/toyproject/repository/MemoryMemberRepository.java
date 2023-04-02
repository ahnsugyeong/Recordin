//package com.example.toyproject.repository;
//
//import com.example.toyproject.domain.Member;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//
//@Repository
//public class MemoryMemberRepository implements MemberRepository{
//    private static final Map<Long, Member> store = new HashMap<>(); //static
//    private static long sequence = 0L; //static
//
//    public Member save(Member member) {
//        member.setMemberId(++sequence);
//        store.put(member.getMemberId(), member);
//        return member;
//    }
//
//    public Member findByMemberId(Long memberId) {
//        return store.get(memberId);
//    }
//
//    public Optional<Member> findByEmail(String email) {
//        return findAll().stream()
//                .filter(m -> m.getEmail().equals(email))
//                .findFirst();
//    }
//
//    public List<Member> findAll() {
//        return new ArrayList<>(store.values());
//    }
//
//    public void update(Long memberId, Member updateParam) {
//        // 업데이트 전 유효성 검사 필요
//        Member findMember = findByMemberId(memberId);
//        findMember.setEmail(updateParam.getEmail());
//        findMember.setPassword(updateParam.getPassword());
//        findMember.setName(updateParam.getName());
//    }
//
//    public void clearStore() {
//        store.clear();
//    }
//}
//
