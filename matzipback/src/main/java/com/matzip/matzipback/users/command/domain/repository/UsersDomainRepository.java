package com.matzip.matzipback.users.command.domain.repository;

import com.matzip.matzipback.users.command.domain.aggregate.Users;

import java.util.Optional;

public interface UsersDomainRepository {

    Users save(Users users);

    Optional<Users> findByUserEmail(String userEmail);
}
