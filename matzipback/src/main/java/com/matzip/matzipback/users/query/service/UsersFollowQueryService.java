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

    public List<FollowingUsersDTO> searchFollowingUsers(int page) {

//        long followingUserSeq = CustomUserUtils.getCurrentUserSeq();
        long followingUserSeq = 2L;

        if (page <= 0) {
            page = 1;
        }

        int offset = (page - 1) * 10;
        int size = 10;

        long count = usersFollowMapper.countFollowing(followingUserSeq);

        if (offset > count) {
            page = (int) count / 10 + 1;
            offset = (page - 1) * 10;
        }

        return usersFollowMapper.searchFollowUsersByUserSeqAndPage(followingUserSeq, offset, size);
    }

}
