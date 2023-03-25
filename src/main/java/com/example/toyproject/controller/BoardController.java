package com.example.toyproject.controller;

import com.example.toyproject.SessionConst;
import com.example.toyproject.domain.Board;
import com.example.toyproject.domain.Member;
import com.example.toyproject.repository.BoardRepository;
import com.example.toyproject.repository.MemberRepository;
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
public class BoardController {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    /**
     *board
     */

    /**
     * memberRepository에서는 boarw를 가져올 수 없기 때문에
     * boardRepository에서 FK로 사용한 memberId를 통해 board를 가져와야 한다.
     * member삭제 필요 --> main.html에서 단지 write 버튼의 링크 하나 때문에 필요함.
     * BoardRepostiory에서 findByMemberId메서드 생성 <<중요!
     */
    //@GetMapping("/{memberId}")
    public String boards(@PathVariable long memberId, Model model) {
        List<Board> boards = boardRepository.findByMemberId(memberId);
        Member member = memberRepository.findByMemberId(memberId);
        model.addAttribute("member", member);
        model.addAttribute("boards", boards);
        return "form/board/main";
    }

    /**
     *http path에 memberId를 삭제함
     * 필요한 정보는 session을 통해 매핑
     */
    @GetMapping
    public String boardsV2(@SessionAttribute(name=SessionConst.SIGN_IN_MEMBER, required = false) Member member, Model model) {
        /*if (member == null) {
            return "/";
        }*/
        List<Board> boards = boardRepository.findByMemberId(member.getMemberId());
        model.addAttribute("member", member);
        model.addAttribute("boards", boards);
        return "form/board/main";
    }

    /**
     * 후에 message, error를 구체적으로 설정할 때 board필요
     * (지금은 html에서 사용x 따라서 삭제)
     * member 필요 x -> 현재 취소 버튼 때문에 사용
     */

    //@GetMapping("/{memberId}/add")
    public String addBoardForm(@PathVariable long memberId, Model model) {
        Member member = memberRepository.findByMemberId(memberId);
        model.addAttribute("board", new Board());
        model.addAttribute("member", member);
        return "form/board/addForm";
    }
    @GetMapping("/add")
    public String addBoardFormV2(Model model) {
        model.addAttribute("board", new Board());
        return "form/board/addForm";
    }

    /**
     * return에서 redirect path를 재설정(board의 path를 바꿨기 때문)
     */

    //@PostMapping("/add")
    public String addBoard(@PathVariable long memberId, @ModelAttribute Board board) {
        board.setMemberId(memberId);
        Board savedBoard = boardRepository.save(board);
        log.info("memberId={}, savedBoardId={}", memberId, savedBoard.getBoardId());
        return "redirect:/board/board/" + savedBoard.getBoardId();
    }
    @PostMapping("/add")
    public String addBoardV2(
            @SessionAttribute(name= SessionConst.SIGN_IN_MEMBER, required = false) Member member,
            @ModelAttribute Board board) {
        board.setMemberId(member.getMemberId());
        Board savedBoard = boardRepository.save(board);
        return "redirect:/board/board/" + savedBoard.getBoardId();
    }

    //@GetMapping("/{memberId}/{boardId}/edit")
    public String editForm(@PathVariable long memberId, @PathVariable long boardId, Model model) {
        Member member = memberRepository.findByMemberId(memberId);
        Board board = boardRepository.findByBoardId(boardId);
        model.addAttribute("member", member);
        model.addAttribute("board", board);
        return "form/board/editForm";
    }
    @GetMapping("/{boardId}/edit")
    public String editFormV2(
            @SessionAttribute(name=SessionConst.SIGN_IN_MEMBER, required = false) Member signInMember,
            @PathVariable long boardId, Model model) {
        Member member = memberRepository.findByMemberId(signInMember.getMemberId());
        Board board = boardRepository.findByBoardId(boardId);
        model.addAttribute("member", member);
        model.addAttribute("board", board);
        return "form/board/editForm";
    }


    //@PostMapping("/{memberId}/{boardId}/edit")
    public String edit(@PathVariable Long memberId, @PathVariable Long boardId, @ModelAttribute Board board) {
        boardRepository.update(boardId, board);
        return "redirect:/board/board/{boardId}";
    }
    @PostMapping("/{boardId}/edit")
    public String editV2(@PathVariable Long boardId, @ModelAttribute Board board) {
        boardRepository.update(boardId, board);
        return "redirect:/board/board/{boardId}";
    }

    /**
     * boardId는 고유값이기 때문에 member를 통해 구하지 않아도 된다.
     * main 페이지의 http 경로가 겹칠 수 있어서 아래와 같이 경로를 수정했다.
     */
    @GetMapping("/board/{boardId}")
    public String board(@PathVariable long boardId, Model model) {
        Board board = boardRepository.findByBoardId(boardId);
        model.addAttribute("board", board);
        return "form/board/board";
    }

}

