package com.matzip.matzipback.users.command.infrastructure.repository;

import com.matzip.matzipback.users.command.domain.aggregate.Users;
import com.matzip.matzipback.users.command.domain.repository.UsersDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersInfraRepository extends JpaRepository<Users, Long>, UsersDomainRepository {
}
