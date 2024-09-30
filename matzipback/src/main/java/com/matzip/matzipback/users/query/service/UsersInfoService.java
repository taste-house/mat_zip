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

    public AllUserInfoResponseDTO getAllUserList(String socialYn, String businessVerifiedYn, String influencerYn,    // 필터링 조건
                                                 String orderBy, Integer page, Integer size) {   // 정렬조건, 페이징
        log.info("getAllUserList() 호출 - 필터링 조건: socialYn={}, businessVerifiedYn={}, influencerYn={}, orderBy={}",
                socialYn, businessVerifiedYn, influencerYn, orderBy);

        // 페이지 번호에 따른 offset 계산(offset : 데이터를 건너뛰는 개수)
        int offset = (page -1) * size;

        // 총 유저 수
        long totalUsers = usersInfoMapper.getTotalUserCount(socialYn, businessVerifiedYn, influencerYn);

        // 전체 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalUsers / size);

        // 페이징 된 유저 리스트 가져오기
        List<UserInfoDTO> userList = usersInfoMapper.getAllUserInfo(socialYn, businessVerifiedYn, influencerYn, orderBy, size, offset);

        log.info("getAllUsers() 완료 - 조회된 회원 수: {}", userList.size());

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
