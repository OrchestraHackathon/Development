package com.example.server.util.exception;

import com.example.server.util.BaseResponseStatus;

public class CourseException extends RuntimeException {

    private final BaseResponseStatus status;

    public CourseException(BaseResponseStatus status) {
        this.status = status;
    }
}
