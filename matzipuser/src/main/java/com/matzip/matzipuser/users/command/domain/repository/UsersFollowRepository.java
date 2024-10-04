package com.matzip.matzipuser.users.command.domain.repository;

import com.matzip.matzipuser.users.command.domain.aggregate.Follow;
import com.matzip.matzipuser.users.command.domain.aggregate.FollowId;

public interface UsersFollowRepository {
    boolean existsById(FollowId followId);

    void deleteById(FollowId followId);

    Follow save(Follow newFollow);
}
