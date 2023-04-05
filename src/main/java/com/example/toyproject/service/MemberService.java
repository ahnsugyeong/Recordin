package com.example.toyproject.service;

import com.example.toyproject.domain.Member;
import com.example.toyproject.dto.BoardDto;
import com.example.toyproject.dto.SignInDto;
import com.example.toyproject.dto.SignUpDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface MemberService {
    Long signIn(SignInDto signInDto);
    Long signUp(SignUpDto signUpDto);
    void signOut(HttpSession session);
    void validateDuplicateMember(SignUpDto signUpDto, BindingResult bindingResult);
    //List<BoardDto> getBoardListOfMember(SignInDto signInDto);

}
