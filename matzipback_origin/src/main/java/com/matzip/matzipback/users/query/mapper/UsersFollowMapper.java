package com.matzip.matzipback.users.query.mapper;

import com.matzip.matzipback.users.query.dto.FollowingUsersDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UsersFollowMapper {

    List<FollowingUsersDTO> searchFollowUsersByUserSeqAndPage(
            @Param("followingUserSeq") long followingUserSeq,
            @Param("offset") long offset,
            @Param("size") int size);

    long countFollowing(long followingUserSeq);

    List<FollowingUsersDTO> searchFollowerUsersByUserSeqAndPage(
            @Param("followedUserSeq") long followedUserSeq,
            @Param("offset") long offset,
            @Param("size") int size
    );

    long countFollower(long followedUserSeq);
}
