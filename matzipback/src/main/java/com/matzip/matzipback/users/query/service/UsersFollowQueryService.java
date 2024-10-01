package com.matzip.matzipback.users.query.service;

import com.matzip.matzipback.users.query.dto.FollowingUsersDTO;
import com.matzip.matzipback.users.query.mapper.UsersFollowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersFollowQueryService {

    private final UsersFollowMapper usersFollowMapper;

    public List<FollowingUsersDTO> searchFollowingUsers(long userSeq, long page) {


        if (page <= 0) {
            page = 1;
        }

        long offset = (page - 1) * 10;
        int size = 10;

        long count = usersFollowMapper.countFollowing(userSeq);

        if (offset > count) {
            page = (int) count / 10 + 1;
            offset = (page - 1) * 10;
        }

        return usersFollowMapper.searchFollowUsersByUserSeqAndPage(userSeq, offset, size);
    }

    public List<FollowingUsersDTO> searchFollowerUsers(long userSeq, long page) {


        if (page <= 0) {
            page = 1;
        }

        long offset = (page - 1) * 10;
        int size = 10;

        long count = usersFollowMapper.countFollower(userSeq);

        if (offset > count) {
            page = (int) count / 10 + 1;
            offset = (page - 1) * 10;
        }
        return usersFollowMapper.searchFollowerUsersByUserSeqAndPage(userSeq, offset, size);
    }
}
