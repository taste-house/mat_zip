package com.matzip.matzipback.responsemessage;

import lombok.Getter;

@Getter
public enum ResponseMessage {
    SAVE_SUCCESS("SAVE 성공"),
    SAVE_FAIL("SAVE 실패"),
    UPDATE_SUCCESS("UPDATE 성공"),
    UPDATE_FAIL("UPDATE 실패"),
    DELETE_SUCCESS("DELETE 성공"),
    DELETE_FAIL("DELETE 실패"),
    UNAUTHORIZED("인증이 필요합니다."),
    NOT_FOUND("요청한 리소스를 찾을 수 없습니다."),
    FOUND("요청한 리소스를 찾았습니다."),
    FOLLOW_SUCCESS("팔로우 성공"),
    FOLLOW_DELETE("팔로우 취소"),
    WRONG_REQUEST("잘못된 요청");

    private final String message;

    ResponseMessage(String message) {
        this.message = message;
    }

}
