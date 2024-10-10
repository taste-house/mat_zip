package com.matzip.matzipback.sercurity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDTO {

    private Long userSeq;
    private String userPassword;
    private String userAuth;
}
