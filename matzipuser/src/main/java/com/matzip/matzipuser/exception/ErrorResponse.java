package com.matzip.matzipuser.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 에러에 대한 응답 메시지
public class ErrorResponse {

    private int statusCode;
    private String error;
    private String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.statusCode = errorCode.getHttpStatus().value();
        this.error = errorCode.getHttpStatus().name();
        this.message = errorCode.getMessage();
    }
}
