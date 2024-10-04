package com.matzip.matzipuser.users.command.domain.repository;

import com.matzip.matzipuser.users.command.domain.aggregate.UserActivity;

import java.util.Optional;

public interface UserActivityRepository {

    Optional<UserActivity> findById(long activityUserSeq);

    UserActivity save(UserActivity userActivity);
}
