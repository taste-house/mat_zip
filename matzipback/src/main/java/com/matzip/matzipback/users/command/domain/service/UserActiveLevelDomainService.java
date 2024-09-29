package com.matzip.matzipback.users.command.domain.service;

import com.matzip.matzipback.users.command.domain.aggregate.ActiveLevel;
import com.matzip.matzipback.users.command.domain.repository.UserActiveLevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserActiveLevelDomainService {

    private final UserActiveLevelRepository userActiveLevelRepository;

    @Transactional(readOnly = true)
    public List<ActiveLevel> getAllActiveLevel() {
        return userActiveLevelRepository.findAll();
    }
}
