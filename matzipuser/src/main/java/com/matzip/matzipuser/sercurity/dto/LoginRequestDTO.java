package com.matzip.matzipuser.sercurity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    private String userEmail; // 이메일을 아이디로 사용됨
    private String userPassword;
}
