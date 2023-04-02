//package com.example.toyproject.service;
//
//import com.example.toyproject.SessionConst;
//import com.example.toyproject.domain.Member;
//import com.example.toyproject.domain.board.Board;
//import com.example.toyproject.dto.BoardDto;
//import com.example.toyproject.repository.BoardRepository;
//import jakarta.servlet.http.HttpSession;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class BoardServiceImpl implements BoardService{
//    private BoardRepository boardRepository;
//
//    //for main page
//    @Override
//    public List<BoardDto> getBoardList(Member member) {
//        List<Board> boardList = boardRepository.findByMemberId(member.getMemberId());
//        List<BoardDto> boardDtoList = new ArrayList<>();
//
//        for (Board board : boardList) {
//            BoardDto boardDto = toDto(board);
//            boardDtoList.add(boardDto);
//        }
//        return boardDtoList;
//    }
//
//    //for save
//    @Override
//    public void postBoard(BoardDto boardDto) {
//        boardRepository.save(boardDto.toEntity());
//    }
//
//    //for each board
//    @Override
//    public BoardDto getBoard(long boardId) {
//        Board board = boardRepository.findByBoardId(boardId);
//        BoardDto boardDto = toDto(board);
//        return boardDto;
//    }
//
//    //for update
//    @Override
//    public void updateBoard(BoardDto boardDto) {
//        Board board = boardDto.toEntity();
//        boardRepository.update(boardDto.getBoardId(),board);
//    }
//
//    //이부분은 프로젝트 질문에서 나왔던 부분을 이런식으로 해결하면 어떨까하고 작성한 로직입니다
//    //작동 확인은 아직 해보지 않았습니다.
//    public boolean checkUserBoard(HttpSession session, BoardDto boardDto) {
//        if (session.getAttribute(SessionConst.SIGN_IN_MEMBER) == boardDto.getMemberId()) {
//            return true;
//        }
//        return false;
//    }
//
//    // board -> boardDto method
//    private BoardDto toDto(Board board) {
//        BoardDto boardDto = BoardDto.builder()
//                .memberId(board.getMemberId())
//                .title(board.getTitle())
//                .writer(board.getWriter())
//                .content(board.getContent())
//                .createdDate(board.getCreatedDate())
//                .rate(board.getRate())
//                .imageUrl(board.getImageUrl()).build();
//
//        return boardDto;
//    }
//}
