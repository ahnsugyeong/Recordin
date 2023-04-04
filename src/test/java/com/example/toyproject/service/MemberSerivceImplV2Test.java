package com.example.toyproject.service;

import com.example.toyproject.domain.Member;
import com.example.toyproject.dto.SignInDto;
import com.example.toyproject.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberSerivceImplV2Test {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void signIn() throws Exception{
        Member build = Member.builder()
                .name("aaa")
                .email("gmail.com")
                .password("bbb").build();

        memberRepository.save(build);

        SignInDto signInDto = new SignInDto("gmail.com", "bbb");
        Long memberId = memberService.signIn(signInDto);

        assertThat(memberId).isEqualTo(memberRepository.findById(memberId).get().getId());

    }

    @Test
    void validateDuplicateMember() {
    }
}