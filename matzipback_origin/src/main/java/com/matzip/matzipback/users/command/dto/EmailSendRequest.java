package com.matzip.matzipback.users.command.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailSendRequest {
    private String userEmail;
    private String userName;
}
