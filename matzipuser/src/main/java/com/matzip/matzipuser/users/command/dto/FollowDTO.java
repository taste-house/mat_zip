package com.matzip.matzipuser.users.command.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowDTO {

    private long followingUserSeq;
    private long followedUserSeq;
}
