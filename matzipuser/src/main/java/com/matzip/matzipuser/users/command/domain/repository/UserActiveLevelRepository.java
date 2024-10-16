package com.matzip.matzipuser.users.command.domain.repository;

import com.matzip.matzipuser.users.command.domain.aggregate.ActiveLevel;

import java.util.List;
import java.util.Optional;

public interface UserActiveLevelRepository {
    List<ActiveLevel> findAll();

    ActiveLevel save(ActiveLevel newActiveLevel);

    Optional<ActiveLevel> findById(Long activeLevelSeq);

    void deleteById(Long activeLevelSeq);
}
