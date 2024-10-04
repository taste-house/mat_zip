package com.matzip.matzipuser.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
// 유효성 검사에 대한 에러처리
public class ValidErrorResponse {

    private int statusCode;
    private String error;
    private Map<String, String> messages;

    public ValidErrorResponse(int statusCode, String error, Map<String, String> messages) {
        this.statusCode = statusCode;
        this.error = error;
        this.messages = messages;
    }
}
