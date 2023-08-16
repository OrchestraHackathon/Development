package com.example.server.controller;

import com.example.server.domain.users.PrincipalDetails;
import com.example.server.dto.auth.LoginTokenResponseDto;
import com.example.server.dto.users.UsersDetailResponseDto;
import com.example.server.dto.users.UsersLoginRequestDto;
import com.example.server.dto.users.UsersResponseDto;
import com.example.server.dto.users.UsersSignUpRequestDto;
import com.example.server.service.UsersService;
import com.example.server.util.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/users/me")
    public BaseResponse<UsersDetailResponseDto> detail(@AuthenticationPrincipal PrincipalDetails users) {
        Long usersId = users.getId();
        UsersDetailResponseDto responseDto = usersService.userInfo(usersId);
        return new BaseResponse<>(responseDto);
    }
}
