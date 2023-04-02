package com.example.toyproject.service;

import com.example.toyproject.domain.Member;
import com.example.toyproject.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
public class MemberSerivceImplV2 implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Member signIn(String email, String password) {
        return null;
    }

    @Override
    public void signUp(Member member) {

    }

    @Override
    public void signOut(HttpSession session) {

    }

    @Override
    public void validateDuplicateMember(Member member, BindingResult bindingResult) {

    }
}
