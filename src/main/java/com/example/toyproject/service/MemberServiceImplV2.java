package com.example.toyproject.service;

import com.example.toyproject.domain.Member;

import com.example.toyproject.dto.MemberInfoDto;
import com.example.toyproject.dto.SignInDto;
import com.example.toyproject.dto.SignUpDto;
import com.example.toyproject.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImplV2 implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public MemberInfoDto signIn(SignInDto signInDto) {
        Optional<Member> first = memberRepository.
                findByEmailAndPassword(signInDto.getEmail(), signInDto.getPassword())
                .stream().findFirst();
        if(first.isEmpty())return null;
        MemberInfoDto memberInfoDto = MemberInfoDto.builder()
                .email(first.get().getEmail())
                .password(first.get().getPassword())
                .name(first.get().getName())
                .build();

        return memberInfoDto;
    }

    @Transactional
    @Override
    public void signUp(SignUpDto signUpDto) {
        memberRepository.save(signUpDto.toEntity());
    }

    @Override
    public void signOut(HttpSession session) {
        session.invalidate();
    }

    @Override
    public void validateDuplicateMember(SignUpDto signUpDto, BindingResult bindingResult) {
        List<Member> findMember = memberRepository
                .findByEmailAndPassword(signUpDto.getEmail(), signUpDto.getPassword());
        if (!findMember.isEmpty()) {
            bindingResult.addError(new FieldError(
                    "signUpDto", "email", "이미 사용중인 이메일입니다."));
        }
    }

//    @Override
//    public List<BoardDto> getBoardListOfMember(SignInDto signInDto) {
//        List<Board> boardList = memberRepository.findByMember(signInDto);
//        List<BoardDto> boardDtoList = new ArrayList<>();
//        for (Board board : boardList) {
//            BoardDto boardDto = EntityToBoardDtoV2(board);
//            boardDtoList.add(boardDto);
//        }
//        return boardDtoList;
//    }

//    private BoardDto EntityToBoardDtoV2(Board board) {
//        BoardDto boardDto = null;
//        if (board.getDtype() == BoardCategory.BOOK) {
//            Book book = (Book) board;
//            boardDto = BoardDto.builder()
//                    .id(book.getId())
//                    .member(book.getMember())
//                    .title(book.getTitle())
//                    .rate(book.getRate())
//                    .createdDate(book.getCreatedDate())
//                    .author(book.getAuthor())
//                    .isbn(book.getIsbn())
//                    .dtype(book.getDtype())
//                    .build();
//        } else if (board.getDtype() == BoardCategory.MOVIE) {
//            Movie movie = (Movie) board;
//            boardDto = BoardDto.builder()
//                    .id(movie.getId())
//                    .member(movie.getMember())
//                    .title(movie.getTitle())
//                    .rate(movie.getRate())
//                    .createdDate(movie.getCreatedDate())
//                    .director(movie.getDirector())
//                    .imageURL(movie.getImageURL())
//                    .dtype(movie.getDtype())
//                    .build();
//        }
//        return boardDto;
//    }


}
