package com.matzip.matzipback.users.query.mapper;

import com.matzip.matzipback.users.query.dto.userInfo.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UsersInfoMapper {

    List<UserInfoDTO> getAllUserInfo(@Param("socialYn") String socialYn,    // 명시적 매핑위해 @Param 으로 작성
                                     @Param("socialSite") String socialSite,
                                     @Param("businessVerifiedYn") String businessVerifiedYn,
                                     @Param("influencerYn") String influencerYn,
                                     @Param("userStatus") String userStatus,
                                     @Param("orderBy") String orderBy,
                                     @Param("size") Integer size,
                                     @Param("offset") int offset);

    Long getTotalUserCount(@Param("socialYn") String socialYn,    // 명시적 매핑위해 @Param 으로 작성
                           @Param("socialSite") String socialSite,
                           @Param("businessVerifiedYn") String businessVerifiedYn,
                           @Param("influencerYn") String influencerYn,
                           @Param("userStatus") String userStatus);

    List<UserInfoDTO> searchUserInfo(@Param("searchType") String searchType,
                                     @Param("searchWord") String searchWord,
                                     @Param("socialYn") String socialYn,
                                     @Param("socialSite") String socialSite,
                                     @Param("businessVerifiedYn") String businessVerifiedYn,
                                     @Param("influencerYn") String influencerYn,
                                     @Param("userStatus") String userStatus,
//                                     @Param("userAuth") String userAuth,
                                     @Param("orderBy") String orderBy,
                                     @Param("size") Integer size,
                                     @Param("offset") int offset);

    Long getSearchUserCount(@Param("searchType") String searchType,
                            @Param("searchWord") String searchWord,
                            @Param("socialYn") String socialYn,
                            @Param("socialSite") String socialSite,
                            @Param("businessVerifiedYn") String businessVerifiedYn,
                            @Param("influencerYn") String influencerYn,
                            @Param("userStatus") String userStatus/*,
                            @Param("userAuth") String userAuth*/);
}
