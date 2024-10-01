package com.matzip.matzipback.users.command.application.service;

import com.matzip.matzipback.users.command.domain.service.ActiveLevelDomainService;
import com.matzip.matzipback.users.command.dto.ActiveLevelResDTO;
import com.matzip.matzipback.users.command.dto.CreateActiveLevelRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ActiveLevelService {

    private final ActiveLevelDomainService activeLevelDomainService;

    @Transactional
    public ActiveLevelResDTO saveActiveLevel(CreateActiveLevelRequestDTO createActiveLevelRequestDTO) {
        return activeLevelDomainService.saveActiveLevel(createActiveLevelRequestDTO);
    }
}
