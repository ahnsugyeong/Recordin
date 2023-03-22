package com.example.toyproject.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SignInForm {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}

