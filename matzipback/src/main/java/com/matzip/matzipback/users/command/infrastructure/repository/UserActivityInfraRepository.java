package com.matzip.matzipback.users.command.infrastructure.repository;

import com.matzip.matzipback.users.command.domain.aggregate.UserActivity;
import com.matzip.matzipback.users.command.domain.repository.UserActivityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActivityInfraRepository extends JpaRepository<UserActivity, Long>, UserActivityRepository {
}
