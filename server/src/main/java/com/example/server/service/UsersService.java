package com.example.server.service;

import com.example.server.domain.users.Users;
import com.example.server.dto.users.UsersResponseDto;
import com.example.server.dto.users.UsersSignUpRequestDto;
import com.example.server.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UsersService {

    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;

    public UsersResponseDto signUp(UsersSignUpRequestDto requestDto) {


        Users users = requestDto.toEntity();
        String encodedPassword = passwordEncoder.encode(users.getPassword());
        users.encodePassword(encodedPassword);
        usersRepository.save(users);
        return UsersResponseDto.builder()
                .id(users.getId())
                .email(users.getEmail())
                .username(users.getName())
                .nickname(users.getNickname())
                .build();
    }
}
