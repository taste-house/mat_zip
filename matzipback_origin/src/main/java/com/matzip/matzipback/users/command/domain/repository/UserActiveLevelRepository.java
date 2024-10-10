package com.matzip.matzipback.users.command.domain.repository;

import com.matzip.matzipback.users.command.domain.aggregate.ActiveLevel;

import java.util.List;

public interface UserActiveLevelRepository {
    List<ActiveLevel> findAll();

    ActiveLevel save(ActiveLevel newActiveLevel);
}
