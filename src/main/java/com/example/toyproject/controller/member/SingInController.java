package com.example.toyproject.controller.member;

import com.example.toyproject.domain.Member;
import com.example.toyproject.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class SingInController {
    private final MemberRepository memberRepository;

    @GetMapping("/sign-in")
    public String signInForm(Model model) {
        model.addAttribute("member", new Member());
        return "form/member/signInForm";
    }

    @PostMapping("/sign-in")
    public String signIn(@ModelAttribute Member member, RedirectAttributes redirectAttributes) {

        log.info("sign-in member.email={}", member.getEmail());
        log.info("sign-in member.password={}", member.getPassword());

        // 로그인 로직 추가 필요

//        redirectAttributes.addAttribute("memberId", member.getId());
//        redirectAttributes.addAttribute("status", true);
        return "redirect:/";
    }

}
