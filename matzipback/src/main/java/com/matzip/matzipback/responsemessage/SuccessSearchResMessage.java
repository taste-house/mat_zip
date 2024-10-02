package com.matzip.matzipback.responsemessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SuccessSearchResMessage<T> extends SuccessResMessage{

    private List<T> data;

    public SuccessSearchResMessage(SuccessCode successCode, List<T> data) {
        super(successCode);
        this.data = data;
    }
}
