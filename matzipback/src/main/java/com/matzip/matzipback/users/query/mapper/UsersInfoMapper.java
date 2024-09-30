package com.matzip.matzipback.users.query.mapper;

import com.matzip.matzipback.users.query.dto.userInfo.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UsersInfoMapper {

    List<UserInfoDTO> getAllUserInfo(@Param("socialYn") String socialYn,    // 명시적 매핑위해 @Param 으로 작성
                                     @Param("businessVerifiedYn") String businessVerifiedYn,
                                     @Param("influencerYn") String influencerYn,
                                     @Param("orderBy") String orderBy,
                                     @Param("size") Integer size,
                                     @Param("offset") int offset);

    long getTotalUserCount(@Param("socialYn") String socialYn,    // 명시적 매핑위해 @Param 으로 작성
                           @Param("businessVerifiedYn") String businessVerifiedYn,
                           @Param("influencerYn") String influencerYn);

}
