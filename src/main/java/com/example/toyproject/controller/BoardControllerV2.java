package com.example.toyproject.controller;


import com.example.toyproject.SessionConst;
import com.example.toyproject.domain.Member;
import com.example.toyproject.dto.BoardDto;
import com.example.toyproject.service.BoardService;
import com.example.toyproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardControllerV2 {
    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping
    public String boards(@SessionAttribute(name = SessionConst.SIGN_IN_MEMBER, required = false) Member member, Model model) {
        if (member == null) return "redirect:/";
        log.info("login success");
        List<BoardDto> boards = boardService.getBoardList(member);
        model.addAttribute("member", member);
        model.addAttribute("boards", boards);
        return "form/board/main";
    }

    @GetMapping("/add")
    public String addBoardForm(Model model) {
        model.addAttribute("board", new BoardDto());
        return "form/board/addForm";
    }

    @PostMapping("/add")
    public String addBoard(@SessionAttribute(name = SessionConst.SIGN_IN_MEMBER, required = false) Member member, @ModelAttribute BoardDto boardDto) {
        boardDto.setMember(member);
        Long savedBoardId = boardService.postBoard(boardDto);
        return "redirect:/board/board/" + savedBoardId;
    }

    @GetMapping("/{boardId}/edit")
    public String editForm(@SessionAttribute(name = SessionConst.SIGN_IN_MEMBER, required = false) Member member,
                           @PathVariable Long boardId, Model model) {
        BoardDto boardDto = boardService.getBoard(boardId);
        model.addAttribute("member", member);
        model.addAttribute("board", boardDto);
        return "form/board/editForm";
    }

    @PostMapping("/{boardId}/edit")
    public String edit(@PathVariable Long boardId, @ModelAttribute BoardDto boardDto) {
        boardService.updateBoard(boardId, boardDto);
        return "redirect:/board/board/{boardId}";
    }

    @GetMapping("/board/{boardId}")
    public String board(@PathVariable Long boardId, Model model) {
        BoardDto boardDto = boardService.getBoard(boardId);
        model.addAttribute("board", boardDto);
        model.addAttribute("member", boardDto.getMember());
        return "form/board/board";
    }
}