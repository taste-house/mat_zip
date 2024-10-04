package com.matzip.matzipuser.users.query.dto.userInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserActivityInfoDTO {

    private long userSeq;
    private long activeLevelSeq;
    private String activeLevelName; // 등급 이름
    private long activityPoint; // 활동 포인트
    private int activeLevelStandard; // 등급 기준
    private String influencerYn; // 인기회원 여부
}
