package com.matzip.matzipback.users.command.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailChkRequest {
    private String userEmail;
    private String verificationCode;
}
