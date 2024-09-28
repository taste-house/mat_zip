package com.matzip.matzipback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // IllegalArgumentException 발생 시 사용자에게 메시지와 400 상태 코드를 반환
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        // 예외 메시지를 클라이언트에 반환
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

























}
