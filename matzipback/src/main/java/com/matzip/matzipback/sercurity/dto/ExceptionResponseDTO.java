package com.matzip.matzipback.sercurity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponseDTO {

    private int code;
    private String message;
}
