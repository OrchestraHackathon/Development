package com.example.server.dto.users;

import com.example.server.domain.Status;
import com.example.server.domain.users.Users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersSignInRequestDto {

    private String email;

    private String password;

    private String userName;

    private String userNickName;

    // dto -> entity
    public Users toEntity() {
        return Users.builder()
                .email(email)
                .password(password)
                .name(userName)
                .nickname(userNickName)
                .status(Status.ACTIVE)
                .role("USER")
                .build();
    }
}
