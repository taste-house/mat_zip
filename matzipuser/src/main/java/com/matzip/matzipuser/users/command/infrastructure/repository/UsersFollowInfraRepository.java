package com.matzip.matzipuser.users.command.infrastructure.repository;

import com.matzip.matzipuser.users.command.domain.aggregate.Follow;
import com.matzip.matzipuser.users.command.domain.aggregate.FollowId;
import com.matzip.matzipuser.users.command.domain.repository.UsersFollowRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersFollowInfraRepository extends JpaRepository<Follow, FollowId>, UsersFollowRepository {
}
