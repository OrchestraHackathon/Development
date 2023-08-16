package com.example.server.dto.users;

import com.example.server.domain.users.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersDetailResponseDto {

    private Long id;
    private String email;
    private String userName;
    private String userNickName;
    private String aboutMe;
    private String profileImageUrl;

    public static UsersDetailResponseDto of(Users users) {
        return UsersDetailResponseDto.builder()
                .id(users.getId())
                .email(users.getEmail())
                .userName(users.getName())
                .userNickName(users.getNickname())
                .aboutMe("한줄 소개를 입력해 주세요")
                .profileImageUrl(users.getProfileImageUrl())
                .build();
    }
}
