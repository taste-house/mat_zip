package com.matzip.matzipuser.responsemessage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {

    BASIC_SAVE_SUCCESS(HttpStatus.CREATED, "저장 성공했습니다."),
    BASIC_UPDATE_SUCCESS(HttpStatus.NO_CONTENT, "수정 성공했습니다."),
    BASIC_DELETE_SUCCESS(HttpStatus.NO_CONTENT, "삭제 성공했습니다."),

    /* 좋아요 등록 */
    LIKE_SUCCESS(HttpStatus.OK, "좋아요 성공"),

    /* 좋아요 취소 */
    LIKE_DELETE_SUCCESS(HttpStatus.OK, "좋아요 취소"),

    /* 조회 성공 */
    BASIC_GET_SUCCESS(HttpStatus.OK, "조회 성공"),

    /* 회원 탈퇴 */
    USER_DELETION_SUCCESS(HttpStatus.OK, "탈퇴가 성공적으로 처리되었습니다.");



    private final HttpStatus httpStatus;
    private final String message;
}
