package com.matzip.matzipback.users.command.infrastructure.repository;

import com.matzip.matzipback.users.command.domain.aggregate.Follow;
import com.matzip.matzipback.users.command.domain.aggregate.FollowId;
import com.matzip.matzipback.users.command.domain.repository.UsersFollowRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersFollowInfraRepository extends JpaRepository<Follow, FollowId>, UsersFollowRepository {
}
