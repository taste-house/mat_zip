package com.matzip.matzipuser.users.command.application.service;

import com.matzip.matzipuser.users.command.application.dto.UpdateActiveLevelDTO;
import com.matzip.matzipuser.users.command.application.dto.UpdateUserActivityPointDTO;
import com.matzip.matzipuser.users.command.domain.service.ActiveLevelDomainService;
import com.matzip.matzipuser.users.command.application.dto.CreateActiveLevelRequestDTO;
import com.matzip.matzipuser.users.command.domain.service.UserActivityDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ActiveLevelService {

    private final ActiveLevelDomainService activeLevelDomainService;
    private final UserActivityDomainService userActivityDomainService;

    // 등급 레벨 엔티티 저장
    @Transactional
    public boolean saveActiveLevel(CreateActiveLevelRequestDTO createActiveLevelRequestDTO) {
        return activeLevelDomainService.saveActiveLevel(createActiveLevelRequestDTO);
    }

    // 회원의 포인트 수정
    @Transactional
    public void updateUserPoint(UpdateUserActivityPointDTO updateUserActivityPointDTO) {
        userActivityDomainService.updateUserActivityPoint(updateUserActivityPointDTO);
    }

    // 활동 등급 수정
    @Transactional
    public void updateActiveLevel(UpdateActiveLevelDTO updateActiveLevelDTO) {
        activeLevelDomainService.updateActiveLevel(updateActiveLevelDTO);
    }

    // 활동 등급 삭제
    @Transactional
    public void deleteActiveLevel(Long activeLevelSeq) {

        activeLevelDomainService.deleteActiveLevel(activeLevelSeq);
    }
}
