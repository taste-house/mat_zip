package com.matzip.matzipback.users.query.dto.userInfo;

import com.matzip.matzipback.report.query.dto.PenaltyDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserInfoDTO {

    private long userSeq;
    private String userEmail;
    private String userNickname;
    private String socialYn;
    private String socialSite;
    private String businessVerifiedYn;
    private String penaltyYn;
    private String userStatus;
    private LocalDateTime userRegDate;
    private LocalDateTime userDeleteDate;

    private PenaltyDTO penalty; // 패널티 정보
    private UserActivityInfoDTO activityInfo;  // 활동도

}
