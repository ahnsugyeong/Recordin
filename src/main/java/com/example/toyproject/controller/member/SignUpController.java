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
public class SignUpController {
    private final MemberRepository memberRepository;

    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute("member", new Member());
        return "form/member/signUpForm";
    }

    @PostMapping("/sign-up")
    public String signUpMember(@ModelAttribute Member member, RedirectAttributes redirectAttributes) {

        log.info("member.email={}", member.getEmail());
        log.info("member.password={}", member.getPassword());
        log.info("member.userName={}", member.getUserName());

        Member savedMember = memberRepository.save(member);
        redirectAttributes.addAttribute("memberId", savedMember.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/";
    }
}
