package com.matzip.matzipback.users.command.infrastructure.repository;

import com.matzip.matzipback.users.command.domain.aggregate.ActiveLevel;
import com.matzip.matzipback.users.command.domain.repository.UserActiveLevelRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersActiveLevelInfraRepository extends JpaRepository<ActiveLevel, Long>, UserActiveLevelRepository {
}
