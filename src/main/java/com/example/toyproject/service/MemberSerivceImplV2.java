package com.example.toyproject.service;

import com.example.toyproject.domain.Member;
import com.example.toyproject.dto.SignInDto;
import com.example.toyproject.dto.SignUpDto;
import com.example.toyproject.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberSerivceImplV2 implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Long signIn(SignInDto signInDto) {
        Optional<Member> first = memberRepository.
                findByEmailAndPassword(signInDto.getEmail(), signInDto.getPassword())
                .stream().findFirst();
        return first.get().getId();
    }

    @Override
    public void signUp(SignUpDto signUpDto) {
        memberRepository.save(signUpDto.toEntity());
    }

    @Override
    public void signOut(HttpSession session) {
        session.invalidate();
    }

    @Override
    public void validateDuplicateMember(SignUpDto signUpDto, BindingResult bindingResult) {
        List<Member> findMember = memberRepository.findByEmailAndPassword(signUpDto.getEmail(), signUpDto.getPassword());
        if (findMember.isEmpty()) {
            bindingResult.addError(new FieldError(
                    "member", "email", "이미 사용중인 이메일입니다."));
        }
    }

//    @Override
//    public List<BoardDto> getBoardListOfMember(SignInDto signInDto) {
//
//    }

}
