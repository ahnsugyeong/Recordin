//package com.example.toyproject.dto;
//
//import com.example.toyproject.domain.board.Board;
//import com.example.toyproject.domain.board.Book;
//import lombok.*;
//
//import java.time.LocalDate;
//
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//public class BoardDto {
//    private Long boardId;
//    private Long memberId;  // FK
//    private String title;
//    private String writer;
//    private String content;
//    private LocalDate createdDate;
//    private Integer rate;
//    private String imageUrl;
//
//
//
//    public Book toEntity() {
//        Book book = Book.builder()
//                .memberId(memberId)
//                .title(title)
//                .writer(writer)
//                .content(content)
//                .createdDate(createdDate)
//                .rate(rate)
//                .imageUrl(imageUrl).build();
//        return board;
//    }
//
//    @Builder
//    public BoardDto(Long memberId, String title, String writer, String content,
//                    LocalDate createdDate, Integer rate, String imageUrl) {
//        this.memberId = memberId;
//        this.title = title;
//        this.writer = writer;
//        this.content = content;
//        this.createdDate = createdDate;
//        this.rate = rate;
//        this.imageUrl = imageUrl;
//    }
//}
