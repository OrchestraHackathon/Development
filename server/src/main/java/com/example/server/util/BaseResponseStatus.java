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
    NONE_USER(false, 2010, "존재하지 않는 회원입니다.");
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
