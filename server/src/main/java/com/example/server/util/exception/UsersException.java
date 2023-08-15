package com.example.server.util.exception;

import com.example.server.util.BaseResponseStatus;
import lombok.Getter;

@Getter
public class UsersException extends RuntimeException {

    private final BaseResponseStatus status;

    public UsersException(BaseResponseStatus status) {
        this.status = status;
    }
}
