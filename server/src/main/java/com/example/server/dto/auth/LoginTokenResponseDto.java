package com.example.server.dto.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginTokenResponseDto {

    private Long usersId;
    private String accessToken;
    private String refreshToken;
}
