package com.matzip.matzipback.responsemessage;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SuccessSearchResMessage<T> extends SuccessResMessage{

    private List<T> data;
    private T data2;

    public SuccessSearchResMessage(SuccessCode successCode, List<T> data) {
        super(successCode);
        this.data = data;
    }

    public SuccessSearchResMessage(SuccessCode successCode, T data2) {
        super(successCode);
        this.data2 = data2;
    }
}