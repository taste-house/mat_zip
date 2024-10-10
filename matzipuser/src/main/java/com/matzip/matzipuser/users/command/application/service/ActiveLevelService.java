package com.matzip.matzipuser.users.command.application.service;

import com.matzip.matzipuser.users.command.application.dto.UpdateUserActivityPointDTO;
import com.matzip.matzipuser.users.command.domain.service.ActiveLevelDomainService;
import com.matzip.matzipuser.users.command.application.dto.ActiveLevelResDTO;
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

    @Transactional
    public ActiveLevelResDTO saveActiveLevel(CreateActiveLevelRequestDTO createActiveLevelRequestDTO) {
        return activeLevelDomainService.saveActiveLevel(createActiveLevelRequestDTO);
    }

    public void updateUserPoint(UpdateUserActivityPointDTO updateUserActivityPointDTO) {
        userActivityDomainService.updateUserActivityPoint(updateUserActivityPointDTO);
    }
}
