package com.matzip.matzipuser.users.command.domain.service;

import com.matzip.matzipuser.exception.ErrorCode;
import com.matzip.matzipuser.exception.RestApiException;
import com.matzip.matzipuser.users.command.application.dto.UpdateActiveLevelDTO;
import com.matzip.matzipuser.users.command.domain.aggregate.ActiveLevel;
import com.matzip.matzipuser.users.command.domain.repository.UserActiveLevelRepository;
import com.matzip.matzipuser.users.command.application.dto.CreateActiveLevelRequestDTO;
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

    public boolean saveActiveLevel(CreateActiveLevelRequestDTO createActiveLevelRequestDTO) {
        ActiveLevel newActiveLevel = modelMapper.map(createActiveLevelRequestDTO, ActiveLevel.class);

        ActiveLevel savedActiveLevel = userActiveLevelRepository.save(newActiveLevel);

        return savedActiveLevel != null;
    }

    // 활동 등급 수정
    public void updateActiveLevel(UpdateActiveLevelDTO updateActiveLevelDTO) {

        ActiveLevel foundActiveLevel = userActiveLevelRepository
                .findById(updateActiveLevelDTO.getActiveLevelSeq())
                .orElseThrow(() -> new RestApiException(ErrorCode.NOT_FOUND));

        modelMapper.map(updateActiveLevelDTO, foundActiveLevel);
    }

    // 활동 등급 삭제
    public void deleteActiveLevel(Long activeLevelSeq) {

        userActiveLevelRepository.deleteById(activeLevelSeq);
    }
}
