package com.example.toyproject.controller;


import com.example.toyproject.SessionConst;
import com.example.toyproject.domain.Member;
import com.example.toyproject.domain.board.Board;
import com.example.toyproject.dto.BoardDto;
import com.example.toyproject.dto.MemberInfoDto;
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
    //private final MemberService memberService;



    @GetMapping
    public String boards(@SessionAttribute(name = SessionConst.SIGN_IN_MEMBER, required = false)
                                     MemberInfoDto memberInfoDto, Model model) {
        if (memberInfoDto == null) return "redirect:/";
        //List<BoardDto> boards = boardService.getBoardList(member);

        List<BoardDto> boardList = boardService.getBoardList(memberInfoDto);
        model.addAttribute("member", memberInfoDto);
        model.addAttribute("boards", boardList);
        return "form/board/main";
    }


    @GetMapping("/add")
    public String addBoardForm(Model model) {
        model.addAttribute("board", new BoardDto());
        return "form/board/addForm";
    }

    @PostMapping("/add")
    public String addBoard(@SessionAttribute(name = SessionConst.SIGN_IN_MEMBER, required = false)
                                       MemberInfoDto memberInfoDto, @ModelAttribute BoardDto boardDto) {
        //boardDto.setMember(member);
        Long savedBoardId = boardService.postBoard(boardDto,memberInfoDto);
        return "redirect:/board/board/" + savedBoardId;
    }

    @GetMapping("/{boardId}/edit")
    public String editForm(@SessionAttribute(name = SessionConst.SIGN_IN_MEMBER, required = false)
                                       MemberInfoDto memberInfoDto, @PathVariable Long boardId, Model model) {
        BoardDto boardDto = boardService.getBoard(boardId);
        model.addAttribute("member", memberInfoDto);
        model.addAttribute("board", boardDto);
        return "form/board/editForm";
    }

    @PostMapping("/{boardId}/edit")
    public String edit(@PathVariable Long boardId, @ModelAttribute("board") BoardDto boardDto) {
        // 못 받아 옴  --> 새로운 dto 객체 형성에 id 정보가 없어지기 때문..?
        //log.info("board controller edit id = {}", boardDto.getId());
        //log.info("board controller edit title = {}", boardDto.getTitle());

        boardService.updateBoard(boardDto, boardId);

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
