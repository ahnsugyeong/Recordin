package com.example.toyproject.controller.board;

import com.example.toyproject.domain.Board;
import com.example.toyproject.domain.BoardRepository;
import com.example.toyproject.domain.Member;
import com.example.toyproject.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/{boardId}")
    public String board(@PathVariable long boardId, Model model) {
        Board board = boardRepository.findById(boardId);
        Member member = memberRepository.findById(board.getId());
        model.addAttribute("board", board);
        model.addAttribute("member", member);
        return "form/board/board";
    }
}
