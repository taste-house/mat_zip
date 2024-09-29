package com.matzip.matzipback.users.command.application.service;

import com.matzip.matzipback.users.command.domain.aggregate.ActiveLevel;
import com.matzip.matzipback.users.command.domain.service.ActiveLevelDomainService;
import com.matzip.matzipback.users.command.dto.ActiveLevelResDTO;
import com.matzip.matzipback.users.command.dto.CreateActiveLevelReqDTO;
import com.matzip.matzipback.users.command.dto.UpdateActiveLevelReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ActiveLevelService {

    private final ActiveLevelDomainService activeLevelDomainService;

    @Transactional
    public ActiveLevelResDTO saveActiveLevel(CreateActiveLevelReqDTO createActiveLevelReqDTO) {
        return activeLevelDomainService.saveActiveLevel(createActiveLevelReqDTO);
    }

    @Transactional
    public ActiveLevelResDTO updateActiveLevel(UpdateActiveLevelReqDTO updateActiveLevelReqDTO) {
        return activeLevelDomainService.updateLevelDomainService(updateActiveLevelReqDTO);
    }
}
