package com.example.server.controller;

import com.example.server.dto.auth.LoginTokenResponseDto;
import com.example.server.dto.users.UsersLoginRequestDto;
import com.example.server.dto.users.UsersResponseDto;
import com.example.server.dto.users.UsersSignUpRequestDto;
import com.example.server.service.UsersService;
import com.example.server.util.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/users/sign-up")
    public BaseResponse<UsersResponseDto> join(@RequestBody UsersSignUpRequestDto signUpRequestDto) {
        UsersResponseDto responseDto = usersService.signUp(signUpRequestDto);
        return new BaseResponse<>(responseDto);
    }

    @PostMapping("/users/login")
    public BaseResponse<LoginTokenResponseDto> login(@RequestBody UsersLoginRequestDto loginRequestDto) {
        LoginTokenResponseDto loginResponseDto = usersService.login(loginRequestDto);
        log.info("로그인 : [{}]", loginResponseDto.getUsersId());
        return new BaseResponse<>(loginResponseDto);
    }
}
