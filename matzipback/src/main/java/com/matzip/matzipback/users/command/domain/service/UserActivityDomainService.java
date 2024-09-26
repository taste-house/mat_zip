package com.matzip.matzipback.users.command.domain.service;

import com.matzip.matzipback.users.command.domain.aggregate.UserActivity;
import com.matzip.matzipback.users.command.domain.repository.UserActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserActivityDomainService {

    private final UserActivityRepository userActivityRepository;

    // 유저 활동 포인트를 업데이트 하는 메서드
    @Transactional
    public void updateUserActivityPoint(int point) {

//        long activityUserSeq = CustomUserUtils.getCurrentUserSeq();
        long activityUserSeq = 2L;

        UserActivity foundUserActivity = userActivityRepository.findById(activityUserSeq).orElse(null);

        foundUserActivity.changePoint(point);
    }

    // 유저 활동 정보 만들기
    public void saveUserActivity(UserActivity userActivity) {
        userActivityRepository.save(userActivity);
    }

}
