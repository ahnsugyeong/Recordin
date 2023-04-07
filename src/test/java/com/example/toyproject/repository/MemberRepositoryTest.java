//package com.example.toyproject.repository;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//class MemberRepositoryTest {
//    @Autowired MemberRepository memberRepository;
//
//    @Test
//    public void signUp() {
//        Member member = memberRepository.save(new Member("hello3@gmail.com", "123", "hello3"));
//
//
//        Assertions.assertThat(member.getMemberId()).isEqualTo(3);
//
//    }
//
//}