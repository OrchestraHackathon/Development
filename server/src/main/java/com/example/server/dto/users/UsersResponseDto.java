package com.example.server.dto.users;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UsersResponseDto {

    private Long id;
    private String email;
    private String username;
    private String nickname;
}
