package com.example.toyproject.controller;

import com.example.toyproject.SessionConst;
import com.example.toyproject.domain.Member;
import com.example.toyproject.dto.SignInDto;
import com.example.toyproject.service.MemberServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberService;

    /**
     * sign-up
     */
    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute("member", new Member());
        return "form/member/signUpForm";
    }

    @PostMapping("/sign-up")
    public String signUpMember(@Validated @ModelAttribute Member member, BindingResult bindingResult) {
        // 중복 이메일 검사
        memberService.validateDuplicateMember(member, bindingResult);
        if (bindingResult.hasErrors()) {
            return "form/member/signUpForm";
        }
        memberService.signUp(member); // 회원가입
        return "redirect:sign-in";
    }

    /**
     * sign-in
     */
    @GetMapping("/sign-in")
    public String signInForm(@ModelAttribute("signInForm") SignInDto form) {
        return "form/member/signInForm";
    }

    @PostMapping("/sign-in")
    public String signIn(@Validated @ModelAttribute("signInForm") SignInDto form, BindingResult bindingResult, HttpServletRequest request) {
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
    /**
     * sign-out
     */
    @PostMapping("/sign-out")
    public String signOut(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        //if session is null, signOut --> 조건문을 사용할 필요가 없어보여서 지움
        memberService.signOut(session);
        return "redirect:/";
    }

}
