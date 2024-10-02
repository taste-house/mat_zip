package com.matzip.matzipback.users.query.dto.userInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
//@Setter
//@AllArgsConstructor
@Builder
public class AllUserInfoResponseDTO {

    private List<UserInfoDTO> userLists;
    private int currentPage;            // 현재 페이지
    private int totalPages;             // 전체 페이지 수
    private long totalUsers;            // 총 회원 수

}
