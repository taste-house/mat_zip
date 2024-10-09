package com.matzip.matzipuser.users.command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailChkRequest {
    private String userEmail;
    private String verificationCode;
}
