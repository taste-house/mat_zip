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

    public SuccessResMessage() {}

    public SuccessResMessage(SuccessCode successCode) {
        this.statusCode = successCode.getHttpStatus().value();
        this.success = successCode.getHttpStatus().name();
        this.message = successCode.getMessage();
    }
}
