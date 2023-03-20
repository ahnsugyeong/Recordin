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

import java.time.LocalDate;

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
    public String signUpMember(@ModelAttribute Member member, Model model) {
        Member savedMember = memberRepository.save(member); // 회원가입
        model.addAttribute("member", savedMember);
        return "redirect:sign-in";
    }

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

    @GetMapping("/board/{memberId}")
    public String boards(@PathVariable long memberId, Model model) {
        Member member = memberRepository.findByMemberId(memberId);
        model.addAttribute("member", member);
        return "form/board/main";
    }

    @GetMapping("/board/{memberId}/add")
    public String addBoardForm(@PathVariable long memberId, Model model) {
        Member member = memberRepository.findByMemberId(memberId);
        model.addAttribute("member", member);
        model.addAttribute("board", new Board());
        return "form/board/addForm";
    }

    @PostMapping("/board/{memberId}/add")
    public String addBoard(@PathVariable long memberId, @ModelAttribute Board board, Model model) {
        System.out.println("MainController.addBoard");
        Member member = memberRepository.findByMemberId(memberId);
        model.addAttribute("member", member);
        // test data -> 추후 thymeleaf 통하여 ModelAttribute로 값 받아와서 저장하는 것으로 수정 필요
        board = new Board();
        board.setTitle("title");
        board.setWriter("writer");
        board.setContent("content");
        board.setCreatedDate(LocalDate.now());
        board.setRate(3);
        board.setImageUrl("");
        Board savedBoard = boardRepository.save(board);
        log.info("memberId={}, savedBoardId={}", memberId, savedBoard.getBoardId());
        return "redirect:/board/{memberId}/" + savedBoard.getBoardId();
    }

    @GetMapping("/board/{memberId}/{boardId}/edit")
    public String editForm(@PathVariable Long memberId, @PathVariable Long boardId, Model model) {
        Member member = memberRepository.findByMemberId(memberId);
        Board board = boardRepository.findByBoardId(boardId);
        model.addAttribute("member", member);
        model.addAttribute("board", board);
        return "form/board/editForm";
    }

    @PostMapping("/board/{memberId}/{boardId}/edit")
    public String edit(@PathVariable Long memberId, @PathVariable Long boardId, @ModelAttribute Board board) {
        boardRepository.update(boardId, board);
        return "redirect:/board/{memberId}/{boardId}";
    }


    @GetMapping("/board/{memberId}/{boardId}")
    public String board(@PathVariable long memberId, @PathVariable long boardId, Model model) {
        Board board = boardRepository.findByBoardId(boardId);
        Member member = memberRepository.findByMemberId(memberId);
        model.addAttribute("board", board);
        model.addAttribute("member", member);
        return "form/board/board";
    }

}
