
package com.example.toyproject.dto;

import com.example.toyproject.domain.Member;
import com.example.toyproject.domain.board.Board;
import com.example.toyproject.domain.board.Book;
import com.example.toyproject.domain.board.Movie;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private Member member;
    private String title;
    private String content;
    private int rate;
    private LocalDateTime createdDate;
    private String dtype = "B";
    private String creator; // Book: author, Movie: director
    private String imageURL;

    public Board toEntity() {
        Board board = null;
        if (dtype.equals("B")) {
            board = Book.builder()
                    .id(id)
                    .member(member)
                    .title(title)
                    .content(content)
                    .rate(rate)
                    .createdDate(createdDate)
                    .author(creator)
                    .imageURL(imageURL)
                    .dtype(dtype)
                    .build();
            return board;
        } else {                //else if (dtype.equals("M")) --> category 설정이 가능해지면

            board = Movie.builder()
                    .id(id)
                    .member(member)
                    .title(title)
                    .content(content)
                    .rate(rate)
                    .createdDate(createdDate)
                    .director(creator)
                    .imageURL(imageURL)
                    .dtype(dtype)
                    .build();
        }
        return board;
    }

    @Builder
    public BoardDto(Long id, Member member, String title, String content,
                    Integer rate, LocalDateTime createdDate, String creator, String imageURL, String dtype) {
        this.id = id;
        this.member = member;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.rate = rate;
        this.creator = creator;
        this.imageURL = imageURL;
        this.dtype = dtype;
    }

}





