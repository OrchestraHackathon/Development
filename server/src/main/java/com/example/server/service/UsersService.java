package com.example.server.service;

import com.example.server.domain.users.Users;
import com.example.server.dto.auth.LoginTokenResponseDto;
import com.example.server.dto.users.UsersDetailResponseDto;
import com.example.server.dto.users.UsersLoginRequestDto;
import com.example.server.dto.users.UsersResponseDto;
import com.example.server.dto.users.UsersSignUpRequestDto;
import com.example.server.repository.UsersRepository;
import com.example.server.util.exception.UsersException;
import com.example.server.util.jwt.JwtProvider;
import com.example.server.util.jwt.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.server.util.BaseResponseStatus.INVALID_EMAIL_OR_PASSWORD;
import static com.example.server.util.BaseResponseStatus.NONE_USER;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UsersService {

    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;
    private final JwtProvider jwtProvider;

    @Value("${default.image.url}")
    private String defaultProfileImageUrl;

    public UsersResponseDto signUp(UsersSignUpRequestDto requestDto) {


        log.info("user service 진입");
        Users users = requestDto.toEntity(defaultProfileImageUrl);
        String encodedPassword = passwordEncoder.encode(users.getPassword());
        users.encodePassword(encodedPassword);
        usersRepository.save(users);
        log.info("회원가입 완료!");
        return UsersResponseDto.builder()
                .id(users.getId())
                .email(users.getEmail())
                .username(users.getName())
                .nickname(users.getNickname())
                .build();
    }

    public LoginTokenResponseDto login(UsersLoginRequestDto loginRequestDto) {
        Users user = usersRepository.findByEmail(loginRequestDto.getEmail())
                .filter(u -> passwordEncoder.matches(loginRequestDto.getPassword(), u.getPassword()))
                .orElseThrow(() -> new UsersException(INVALID_EMAIL_OR_PASSWORD));

        String email = user.getEmail();
        Token token = jwtProvider.createToken(email);


        return LoginTokenResponseDto.builder()
                .usersId(user.getId())
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }

    public UsersDetailResponseDto userInfo(Long usersId) {
        Users user = usersRepository.findById(usersId)
                .orElseThrow(() -> new UsersException(NONE_USER));
        return UsersDetailResponseDto.of(user);
    }
}
