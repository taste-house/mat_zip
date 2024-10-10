package com.matzip.matzipback.users.query.dto.userInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtherUserInfoDto {

    private long userSeq;
    private String userNickname;
    private int activityPoint;
    private String activeLevelName;
    private String influencerYn;

    private int listCount;
    private int reviewCount;
    private int followerCount;
    private int followingCount;
}
