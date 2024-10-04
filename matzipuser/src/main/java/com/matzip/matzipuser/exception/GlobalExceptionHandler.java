package com.matzip.matzipuser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

//    // IllegalArgumentException 발생 시 사용자에게 메시지와 400 상태 코드를 반환
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
//        // 예외 메시지를 클라이언트에 반환
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//    }
//
//    // MethodArgumentNotValidException 발생 시 메시지 반환
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ValidErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error ->
//                errors.put(error.getField(), error.getDefaultMessage())
//        );
//        return ResponseEntity.badRequest()
//                .body(new ValidErrorResponse(
//                        HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), errors));
//    }
}
