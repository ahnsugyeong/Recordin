package com.example.toyproject.controller;

import com.example.toyproject.domain.Member;
import com.example.toyproject.repository.BoardRepository;
import com.example.toyproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberRepository memberRepository;

    /**
     * sign-up
     */
    @GetMapping("/member/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute("member", new Member());
        return "form/member/signUpForm";
    }

    @PostMapping("/member/sign-up")
    public String signUpMember(@ModelAttribute Member member, Model model) {
        Member savedMember = memberRepository.save(member); // 회원가입
        model.addAttribute("member", savedMember);
        return "redirect:sign-in";
    }

    /**
     * sign-in
     */
    @GetMapping("/member/sign-in")
    public String signInForm(@ModelAttribute Member member, Model model) {
        member.setPassword("");
        model.addAttribute("member", member);
        return "form/member/signInForm";
    }

    @PostMapping("/member/sign-in")
    public String signIn(@ModelAttribute Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        // 로그인 검증 로직 추가 필요
        // RedirectAttributes 이용하여 status, memberId 값 전달?
        return "redirect:/board/" + findMember.getMemberId();
    }

}
