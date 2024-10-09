package com.matzip.matzipuser.users.command.domain.service;

import com.matzip.matzipuser.common.util.CustomUserUtils;
import com.matzip.matzipuser.exception.ErrorCode;
import com.matzip.matzipuser.exception.RestApiException;
import com.matzip.matzipuser.users.command.application.dto.UpdateUserActivityPointDTO;
import com.matzip.matzipuser.users.command.domain.aggregate.ActiveLevel;
import com.matzip.matzipuser.users.command.domain.aggregate.UserActivity;
import com.matzip.matzipuser.users.command.domain.repository.UserActivityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserActivityDomainService {

    private final UserActivityRepository userActivityRepository;
    private final ActiveLevelDomainService activeLevelDomainService;
    private final ModelMapper modelMapper;

    // 유저 활동 포인트를 업데이트 하는 메서드
    @Transactional
    public void updateUserActivityPoint(UpdateUserActivityPointDTO updateUserActivityPointDTO) {

        // 만약 신고당한 후에 관리자가 삭제하는 경우 관리자의 포인트가 깎이게 될 수 있음.
        long loginUserSeq = CustomUserUtils.getCurrentUserSeq();
        long activityUserSeq = updateUserActivityPointDTO.getActivityUserSeq();

        if (loginUserSeq != activityUserSeq) {
            throw new RestApiException(ErrorCode.UNAUTHORIZED_REQUEST);
        }

        UserActivity foundUserActivity = userActivityRepository
                .findById(activityUserSeq)
                .orElse(null);

        if (foundUserActivity == null) {
            foundUserActivity = saveUserActivity(updateUserActivityPointDTO);
        }

        updateUserActivityLevel(updateUserActivityPointDTO);

        modelMapper.map(updateUserActivityPointDTO, foundUserActivity);

    }

    // 유저 활동 정보 만들기
    public UserActivity saveUserActivity(UpdateUserActivityPointDTO updateUserActivityPointDTO) {
        UserActivity userActivity = modelMapper.map(updateUserActivityPointDTO, UserActivity.class);
        return userActivityRepository.save(userActivity);
    }


    // 유저 회원 등급 조정
    public void updateUserActivityLevel(UpdateUserActivityPointDTO updateUserActivityPointDTO) {

        long activeLevelSeq = calculateLevel(updateUserActivityPointDTO.getActivityPoint());

        updateUserActivityPointDTO.setActivityLevelSeq(activeLevelSeq);
    }

    // 유저 회원 등급 조정
    private long calculateLevel(int point) {
        List<ActiveLevel> activeLevelList = activeLevelDomainService.getAllActiveLevel();
        for (ActiveLevel level : activeLevelList) {
            if (point >= level.getActiveLevelStandard()) {
                return level.getActiveLevelSeq();
            }
        }
        return 1;
    }
}
