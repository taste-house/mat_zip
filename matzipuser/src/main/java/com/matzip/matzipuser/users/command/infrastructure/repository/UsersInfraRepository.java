package com.matzip.matzipuser.users.command.infrastructure.repository;

import com.matzip.matzipuser.users.command.domain.aggregate.Users;
import com.matzip.matzipuser.users.command.domain.repository.UsersDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersInfraRepository extends JpaRepository<Users, Long>, UsersDomainRepository {
}
