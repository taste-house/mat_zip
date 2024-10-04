package com.matzip.matzipuser.users.query.service;

import com.matzip.matzipuser.users.query.dto.userInfo.AllUserInfoResponseDTO;
import com.matzip.matzipuser.users.query.dto.userInfo.OtherUserInfoDto;
import com.matzip.matzipuser.users.query.dto.userInfo.UserDetailInfoDTO;
import com.matzip.matzipuser.users.query.dto.userInfo.UserInfoDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UsersInfoServiceTest {

    @Autowired
    private UsersInfoService usersInfoService;

    @DisplayName("회원 전체 목록 조회 테스트")
    @Test
    void getAllUserInfos() {
        // given : 테스트 초기 설정
        String socialYn = "N";
        String socialSite = null;
        String businessVerifiedYn = "N";
        String influencerYn = "N";
        String userStatus = "active";
        String orderBy = "gradeDESC";
        int page = 1;
        int size = 10;

        // when : 메소드 실행
        AllUserInfoResponseDTO allUsers = usersInfoService.getAllUserList(socialYn, socialSite, businessVerifiedYn, influencerYn, userStatus, orderBy, page, size);

        // then : 예상과 비교
        assertNotNull(allUsers);  // 전체 조회 결과가 null 이 아님을 확인
        assertTrue(!allUsers.getUserLists().isEmpty());  // 반환된 회원 목록이 비어있지 않은지 확인
        assertEquals(page, allUsers.getCurrentPage());  // 현재 페이지가 맞는지 확인
        assertTrue(allUsers.getTotalUsers() > 0);  // 전체 회원 수가 0보다 큰지 확인
        assertTrue(allUsers.getTotalPages() > 0);  // 전체 페이지 수가 0보다 큰지 확인
    }

    @DisplayName("회원 검색 목록 조회 테스트")
    @Test
    void getSearchUserList() {
        // given
        String searchType = "nickname";
        String searchWord = "nick";
        String socialYn = "N";
        String socialSite = null;
        String businessVerifiedYn = null;
        String influencerYn = null;
        String userStatus = null;
        String userAuth = "user";
        String orderBy = "regDateDesc";
        int page = 1;
        int size = 10;

        // when
        AllUserInfoResponseDTO searchResult = usersInfoService.getSearchUserList(searchType, searchWord, socialYn, socialSite, businessVerifiedYn, influencerYn, userStatus, userAuth, orderBy, page, size);

        // then : 예상과 비교
        assertNotNull(searchResult);  // 전체 조회 결과가 null 이 아님을 확인
        assertTrue(!searchResult.getUserLists().isEmpty());  // 반환된 회원 목록이 비어있지 않은지 확인
        assertEquals(page, searchResult.getCurrentPage());  // 현재 페이지가 맞는지 확인
        assertTrue(searchResult.getTotalUsers() > 0);  // 전체 회원 수가 0보다 큰지 확인
        assertTrue(searchResult.getTotalPages() > 0);  // 전체 페이지 수가 0보다 큰지 확인
        assertThat(searchResult.getUserLists())
                .extracting(UserInfoDTO::getUserNickname)
                .contains("nick2", "nick3","dummynick");  // 특정 닉네임이 포함되었는지 확인
    }

    @DisplayName("관리자 및 회원 본인 상세정보 조회 테스트")
    @Test
    void getDetailUserInfo() {
        // given
        Long userSeq = 4L;
        String userAuth = "user";   // 그 회원의 권한

        // when
        UserDetailInfoDTO userDetailInfo = usersInfoService.getDetailUserInfo(userSeq, userAuth);

        // then
        assertNotNull(userDetailInfo);  // 상세 조회 결과가 null이 아닌지 확인
        assertEquals(userSeq, userDetailInfo.getUserSeq());  // 조회된 회원 번호가 맞는지 확인
        assertEquals(userAuth, userDetailInfo.getUserAuth());  // 회원 권한이 맞는지 확인
        assertNotNull(userDetailInfo.getActivityInfo());  // 활동 정보가 있는지 확인
        assertNotNull(userDetailInfo.getBusinessInfo());  // 사업자 정보가 있는지 확인
    }

    @DisplayName("다른 회원 상세정보 조회 테스트")
    @Test
    void getOthersInfo() {
        // given
        Long userSeq = 10L;  // 조회할 회원 시퀀스

        // when
        OtherUserInfoDto otherUserInfo = usersInfoService.getOthersInfo(userSeq);

        // then
        assertNotNull(otherUserInfo);  // 다른 회원 정보가 null이 아닌지 확인
        assertEquals(userSeq, otherUserInfo.getUserSeq());  // 조회된 회원 번호가 맞는지 확인
        assertNotNull(otherUserInfo.getUserNickname());  // 닉네임이 null이 아닌지 확인
        assertEquals("dummy13", otherUserInfo.getUserNickname());
    }
}