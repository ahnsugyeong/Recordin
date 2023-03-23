package com.example.toyproject.controller;

import com.example.toyproject.SessionConst;
import com.example.toyproject.domain.Member;
import com.example.toyproject.repository.MemberRepository;
import com.example.toyproject.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    /**
     * sign-up
     */
    @GetMapping("/member/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute("member", new Member());
        return "form/member/signUpForm";
    }

    @PostMapping("/member/sign-up")
    public String signUpMember(@Validated @ModelAttribute Member member, BindingResult bindingResult) {
        // 중복 이메일 검사
        memberService.validateDuplicateMember(member, bindingResult);
        if (bindingResult.hasErrors()) {
            return "form/member/signUpForm";
        }
        Member savedMember = memberRepository.save(member); // 회원가입
        return "redirect:sign-in";
    }

    /**
     * sign-in
     */
    @GetMapping("/member/sign-in")
    public String signInForm(@ModelAttribute("signInForm") SignInForm form) {
        return "form/member/signInForm";
    }

    @PostMapping("/member/sign-in")
    public String signIn(@Validated @ModelAttribute("signInForm") SignInForm form, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "form/member/signInForm";
        }

        // sign-in
        Member signInMember = memberService.signIn(form.getEmail(), form.getPassword());
        if (signInMember == null) {
            bindingResult.reject("signInFail", "이메일 또는 비밀번호가 올바르지 않습니다.");
            return "form/member/signInForm";
        }
        // 로그인 성공 세션 처리 (세션에 로그인 회원 정보 보관)
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.SIGN_IN_MEMBER, signInMember);
        return "redirect:/";
    }

}
