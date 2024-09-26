package com.matzip.matzipback.users.command.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequest {
    private String userEmail;
    private String userName;
}
