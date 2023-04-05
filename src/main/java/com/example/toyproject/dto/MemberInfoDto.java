package com.example.toyproject.dto;

import com.example.toyproject.domain.board.Board;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberInfoDto {            //session용도, 정보를 통해서 데이터 매핑
    private String name;
    private String email;
    private String password;

    @Builder
    public MemberInfoDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
