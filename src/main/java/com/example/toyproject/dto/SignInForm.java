package com.example.toyproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SignInForm {
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    @Builder
    public SignInForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

}

