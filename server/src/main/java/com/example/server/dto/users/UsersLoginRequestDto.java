package com.example.server.dto.users;

import lombok.Getter;

@Getter
public class UsersLoginRequestDto {

    private String email;
    private String password;
}
