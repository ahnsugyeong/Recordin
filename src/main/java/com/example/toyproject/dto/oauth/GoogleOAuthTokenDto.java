package com.example.toyproject.dto.oauth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GoogleOAuthTokenDto {
    private String access_token;
    private Integer expires_in;
    private String scope;
    private String token_type;
    private String id_token;
}
