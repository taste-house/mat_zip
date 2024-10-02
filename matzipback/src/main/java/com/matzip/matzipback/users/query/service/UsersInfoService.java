package com.matzip.matzipback.users.query.service;

import com.matzip.matzipback.users.query.dto.userInfo.AllUserInfoResponseDTO;
import com.matzip.matzipback.users.query.dto.userInfo.UserInfoDTO;
import com.matzip.matzipback.users.query.mapper.UsersInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersInfoService {

    private final UsersInfoMapper usersInfoMapper;

    public AllUserInfoResponseDTO getAllUserList(String socialYn, String socialSite, String businessVerifiedYn,
                                                 String influencerYn, String userStatus, String orderBy,   // 필터링 조건, 정렬
                                                 Integer page, Integer size) {   // 페이징
        log.info("getAllUserList() 호출 - 필터링 조건: socialYn={}, businessVerifiedYn={}, influencerYn={}, userStatus={}, orderBy={}",
                socialYn, businessVerifiedYn, influencerYn, userStatus, orderBy);

        // 페이지 번호에 따른 offset 계산(offset : 데이터를 건너뛰는 개수)
        int offset = (page -1) * size;

        // 총 유저 수
        Long totalUsers = usersInfoMapper.getTotalUserCount(socialYn, socialSite, businessVerifiedYn, influencerYn, userStatus);

        // 전체 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalUsers / size);

        // 페이징 된 유저 리스트 가져오기
        List<UserInfoDTO> userList = usersInfoMapper.getAllUserInfo(socialYn, socialSite, businessVerifiedYn, influencerYn, userStatus, orderBy, size, offset);

        log.info("getAllUsers() 완료 - 페이지에 조회된 회원 수: {}, 총 회원 수 : {}", userList.size(), totalUsers);

//        AllUserInfoResponseDTO allUserInfoResponseDTO = new AllUserInfoResponseDTO(userList, page, totalPages, totalUsers);
//        return allUserInfoResponseDTO;
        return AllUserInfoResponseDTO.builder()
                .userLists(userList)
                .currentPage(page)
                .totalPages(totalPages)
                .totalUsers(totalUsers)
                .build();
    }


    public AllUserInfoResponseDTO getSearchUserList(String searchType, String searchWord,   // 검색조건
                                                    String socialYn, String socialSite, String businessVerifiedYn,
                                                    String influencerYn, String userStatus,  String userAuth, String orderBy,   // 필터링 조건, 정렬
                                                    Integer page, Integer size) {   // 페이징
        log.info("getSearchUserList() 호출 - 검색 조건: searchType={}, searchWord={}", searchType, searchWord);

        // 페이지 번호에 따른 offset 계산(offset : 데이터를 건너뛰는 개수)
        int offset = (page -1) * size;

        // 유저 검색결과 수
        Long totalUsers = usersInfoMapper.getSearchUserCount(searchType, searchWord,
                socialYn, socialSite, businessVerifiedYn, influencerYn, userStatus, userAuth);

        // 전체 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalUsers / size);

        // 페이징 된 유저 리스트 가져오기
        List<UserInfoDTO> userList = usersInfoMapper.searchUserInfo(searchType, searchWord,
                socialYn, socialSite, businessVerifiedYn, influencerYn, userStatus, userAuth, orderBy, size, offset);

        log.info("getSearchUserList() 완료 - 페이지에 검색된 회원 수: {}, 총 회원 수 : {}", userList.size(), totalUsers);

//        AllUserInfoResponseDTO allUserInfoResponseDTO = new AllUserInfoResponseDTO(userList, page, totalPages, totalUsers);
//        return allUserInfoResponseDTO;
        return AllUserInfoResponseDTO.builder()
                .userLists(userList)
                .currentPage(page)
                .totalPages(totalPages)
                .totalUsers(totalUsers)
                .build();
    }
}
