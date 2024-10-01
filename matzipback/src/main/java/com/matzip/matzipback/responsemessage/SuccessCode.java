package com.matzip.matzipback.responsemessage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {

    BASIC_SAVE_SUCCESS(HttpStatus.CREATED, "저장 성공했습니다."),
    BASIC_UPDATE_SUCCESS(HttpStatus.NO_CONTENT, "수정 성공했습니다."),
    BASIC_DELETE_SUCCESS(HttpStatus.NO_CONTENT, "삭제 성공했습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
