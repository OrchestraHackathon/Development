package com.example.server.util.exception;

import com.example.server.util.BaseResponseStatus;

public class TimeTableException extends RuntimeException{

    private final BaseResponseStatus status;

    public TimeTableException(BaseResponseStatus status) {
        this.status = status;
    }
}
