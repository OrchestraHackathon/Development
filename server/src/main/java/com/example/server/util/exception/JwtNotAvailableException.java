package com.example.server.util.exception;

import com.example.server.util.BaseResponseStatus;
import lombok.Getter;

@Getter
public class JwtNotAvailableException extends RuntimeException {

    private final BaseResponseStatus status;

    public JwtNotAvailableException(BaseResponseStatus status) {
        this.status = status;
    }
}
