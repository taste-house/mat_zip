package com.matzip.matzipback.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
// 원하는 응답 메시지 만들고 싶으면 추가하셔야 합니다.
public enum ErrorCode {
    /*
     * 400 BAD_REQUEST: 잘못된 요청
     */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Invalid request."),

    /* 409 CONFLICT : 서버와의 충돌
    * 저장 실패했을 때 보통 사용하는 상태코드라고 합니다. */
    NOT_SAVED(HttpStatus.CONFLICT, "Not saved."),
    /*
     * 401 UNAUTHORIZED: 인증되지 않은 사용자의 요청
     */
    UNAUTHORIZED_REQUEST(HttpStatus.UNAUTHORIZED, "Unauthorized."),

    /*
     * 403 FORBIDDEN: 권한이 없는 사용자의 요청
     */
    FORBIDDEN_ACCESS(HttpStatus.FORBIDDEN, "Forbidden."),

    /*
     * 404 NOT_FOUND: 리소스를 찾을 수 없음
     */
    NOT_FOUND(HttpStatus.NOT_FOUND, "Not found."),

    /*
     * 405 METHOD_NOT_ALLOWED: 허용되지 않은 Request Method 호출
     */
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "Not allowed method."),

    /*
    * 409 DUPLICATE_ENTRY: 이미 값이 존재
    */
    CONFLICT(HttpStatus.CONFLICT, "Data Conflict"),

    /*
     * 422 UNPROCESSABLE_ENTITY: 요청은 잘 만들어졌지만, 문법 오류로 인하여 따를 수 없습니다.
     */
    UNPROCESSABLE_ENTITY(HttpStatus.UNPROCESSABLE_ENTITY, "Unprocessable Entity"),

    /*
     * 500 INTERNAL_SERVER_ERROR: 내부 서버 오류
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Server error."),

    /* 신고 실패 */
    REPORT_FAIL(HttpStatus.CONFLICT, "Report Fail");


    private final HttpStatus httpStatus;
    private final String message;
}
