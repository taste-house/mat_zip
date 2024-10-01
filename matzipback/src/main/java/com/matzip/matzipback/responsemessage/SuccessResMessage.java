package com.matzip.matzipback.responsemessage;

import com.matzip.matzipback.exception.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResMessage {

    private int statusCode;
    private String success;
    private String message;

    public SuccessResMessage(ErrorCode errorCode) {
        this.statusCode = errorCode.getHttpStatus().value();
        this.success = errorCode.getHttpStatus().name();
        this.message = errorCode.getMessage();
    }
}
