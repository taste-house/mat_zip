package com.matzip.matzipuser.users.query.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersActivityDTO {

    private long userSeq;
    private long activeLevelSeq;
    private String activeLevelName;
    private long activityPoint;
    private String userEmail;
    private String userName;
    private String userNickname;

}
