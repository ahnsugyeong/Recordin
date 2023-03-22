package com.example.toyproject.service;

import com.example.toyproject.domain.Member;
import com.example.toyproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInService {
    private final MemberRepository memberRepository;

    public Member signIn(String email, String password) {
        return memberRepository.findByEmail(email).filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}

