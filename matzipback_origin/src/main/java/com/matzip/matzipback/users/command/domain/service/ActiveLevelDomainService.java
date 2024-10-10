package com.matzip.matzipback.users.command.domain.service;

import com.matzip.matzipback.users.command.domain.aggregate.ActiveLevel;
import com.matzip.matzipback.users.command.domain.repository.UserActiveLevelRepository;
import com.matzip.matzipback.users.command.dto.ActiveLevelResDTO;
import com.matzip.matzipback.users.command.dto.CreateActiveLevelRequestDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActiveLevelDomainService {

    private final UserActiveLevelRepository userActiveLevelRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<ActiveLevel> getAllActiveLevel() {
        return userActiveLevelRepository.findAll();
    }

    public ActiveLevelResDTO saveActiveLevel(CreateActiveLevelRequestDTO createActiveLevelRequestDTO) {
        ActiveLevel newActiveLevel = modelMapper.map(createActiveLevelRequestDTO, ActiveLevel.class);

        ActiveLevel savedActiveLevel = userActiveLevelRepository.save(newActiveLevel);

        return modelMapper.map(savedActiveLevel, ActiveLevelResDTO.class);
    }
}
