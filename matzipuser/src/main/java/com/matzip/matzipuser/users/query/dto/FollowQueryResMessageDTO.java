package com.matzip.matzipuser.users.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FollowQueryResMessageDTO {

    private int code;
    private String message;
    private List<FollowingUsersDTO> followingUsers;
}
