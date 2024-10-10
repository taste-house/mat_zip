package com.matzip.matzipback.users.command.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeleteUserRequest {

    private long userSeq;
    private String userPassword;
}
