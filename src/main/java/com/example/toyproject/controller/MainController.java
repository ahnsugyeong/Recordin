package com.example.toyproject.controller;

import com.example.toyproject.domain.Board;
import com.example.toyproject.repository.BoardRepository;
import com.example.toyproject.domain.Member;
import com.example.toyproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/member/sign-up")
    public String signUpForm(Model model) {
        model.addAttribute("member", new Member());
        return "form/member/signUpForm";
    }

    @PostMapping("/member/sign-up")
    public String signUpMember(@ModelAttribute Member member, RedirectAttributes redirectAttributes) {

        log.info("member.email={}", member.getEmail());
        log.info("member.password={}", member.getPassword());
        log.info("member.userName={}", member.getUserName());

        Member savedMember = memberRepository.save(member);
        redirectAttributes.addAttribute("memberId", savedMember.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/";
    }

    @GetMapping("/member/sign-in")
    public String signInForm(Model model) {
        model.addAttribute("member", new Member());
        return "form/member/signInForm";
    }

    @PostMapping("/member/sign-in")
    public String signIn(@ModelAttribute Member member, RedirectAttributes redirectAttributes) {

        log.info("sign-in member.email={}", member.getEmail());
        log.info("sign-in member.password={}", member.getPassword());

        // 로그인 로직 추가 필요

//        redirectAttributes.addAttribute("memberId", member.getId());
//        redirectAttributes.addAttribute("status", true);
        return "redirect:/";
    }

    @GetMapping("/board/{boardId}")
    public String board(@PathVariable long boardId, Model model) {
        Board board = boardRepository.findById(boardId);
        Member member = memberRepository.findById(board.getId());
        model.addAttribute("board", board);
        model.addAttribute("member", member);
        return "form/board/board";
    }

}
