package com.matzip.matzipback.users.query.dto.userInfo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDetailInfoDTO {

    private long userSeq;
    private String userEmail;
    private String userNickname;
    private String userName;        // 회원 본인만 볼 수 있음
    private String userPhone;       // 회원 본인만 볼 수 있음

    private String socialYn;
    private String socialSite;

    private String penaltyYn;       // 관리자만 볼 수 있음
    private LocalDateTime penaltyStartDate;  // 관리자만 볼 수 있음
    private LocalDateTime penaltyEndDate;    // 관리자만 볼 수 있음
    private String userStatus;      // 관리자만 볼 수 있음
    private String userAuth;
    private LocalDateTime userRegDate;   // 관리자만 볼 수 있음
    private LocalDateTime userDeleteDate; // 관리자만 볼 수 있음

    private int listCount;
    private int reviewCount;
    private int followerCount;
    private int followingCount;

    private String businessVerifiedYn;
    private BusinessLicenseDTO businessInfo;  // 사업자 정보
    private UserActivityInfoDTO activityInfo;   // 활동도
}
