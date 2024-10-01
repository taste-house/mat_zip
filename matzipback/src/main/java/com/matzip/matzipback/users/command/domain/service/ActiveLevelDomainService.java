package com.matzip.matzipback.users.command.domain.service;

import com.matzip.matzipback.users.command.domain.aggregate.ActiveLevel;
import com.matzip.matzipback.users.command.domain.repository.UserActiveLevelRepository;
import com.matzip.matzipback.users.command.dto.ActiveLevelResDTO;
import com.matzip.matzipback.users.command.dto.CreateActiveLevelReqDTO;
import com.matzip.matzipback.users.command.dto.UpdateActiveLevelReqDTO;
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


    public ActiveLevelResDTO saveActiveLevel(CreateActiveLevelReqDTO createActiveLevelReqDTO) {

        ActiveLevel newActiveLevel = modelMapper.map(createActiveLevelReqDTO, ActiveLevel.class);

        ActiveLevel savedActiveLevel = userActiveLevelRepository.save(newActiveLevel);

        return modelMapper.map(savedActiveLevel, ActiveLevelResDTO.class);
    }


    public ActiveLevelResDTO updateLevelDomainService(UpdateActiveLevelReqDTO updateActiveLevelReqDTO) {

        ActiveLevel activeLevel = userActiveLevelRepository.findById(updateActiveLevelReqDTO.getActiveLevelSeq()).orElse(null);
        UpdateActiveLevelReqDTO mapped = modelMapper.map(activeLevel, UpdateActiveLevelReqDTO.class);
        mapped.setActiveLevelName(updateActiveLevelReqDTO.getActiveLevelName());
        mapped.setActiveLevelStandard(updateActiveLevelReqDTO.getActiveLevelStandard());

        ActiveLevel updatedActiveLevel = modelMapper.map(mapped, ActiveLevel.class);

        return modelMapper.map(updatedActiveLevel, ActiveLevelResDTO.class);
    }

}
