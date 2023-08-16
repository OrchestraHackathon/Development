package com.example.server.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {

    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    /**
     * 2000 : Request 오류
     */
    // JWT 예외 - Filter에서 처리
    ACCESS_DENIED(false, 2001, "권한이 없는 유저의 접근입니다."),
    EMPTY_AUTHORIZATION_HEADER(false, 2002, "Authorization 헤더가 존재하지 않습니다."),
    EXPIRED_ACCESS_TOKEN(false, 2003, "이미 만료된 Access 토큰입니다."),
    UNSUPPORTED_TOKEN_TYPE(false, 2004, "지원되지 않는 토큰 형식입니다."),
    MALFORMED_TOKEN_TYPE(false, 2005, "인증 토큰이 올바르게 구성되지 않았습니다."),
    INVALID_SIGNATURE_JWT(false, 2006, "인증 시그니처가 올바르지 않습니다"),
    INVALID_TOKEN_TYPE(false, 2007, "잘못된 토큰입니다."),

    // User 관련 Exception
    NONE_USER(false, 2010, "존재하지 않는 회원입니다."),
    INVALID_EMAIL_OR_PASSWORD(false, 2011, "이메일 혹은 비밀번호가 잘못되었습니다."),

    // Course 관련 Exception
    NONE_COURSE(false, 2020, "존재하지 않는 강의입니다."),

    // TimeTable 관련 Exception
    NONE_TIMETABLE(false, 2030, "시간표가 존재하지 않습니다.");
    /**
     * 3000 : Response 오류
     */

    /**
     * 4000 : Database, Server 오류
     */

    /**
     * 5000 : AWS 오류
     */

    /**
     * 6000 : 보안 이슈
     */

    private final boolean isSuccess;
    private final int responseCode;
    private final String responseMessage;
}
