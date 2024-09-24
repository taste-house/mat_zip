package com.matzip.matzipback.users.command.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {

    private String userName;
    private String userPassword;
    private String userEmail;
    private String userPhone;
    private String userNickname;
    private String userAuth;
}
