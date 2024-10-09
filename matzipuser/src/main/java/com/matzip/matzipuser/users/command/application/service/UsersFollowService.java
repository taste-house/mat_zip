package com.matzip.matzipuser.users.command.application.service;

import com.matzip.matzipuser.users.command.domain.aggregate.Follow;
import com.matzip.matzipuser.users.command.domain.service.UsersFollowDomainService;
import com.matzip.matzipuser.users.command.application.dto.FollowDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsersFollowService {

    private final UsersFollowDomainService usersFollowDomainService;
    private final ModelMapper modelMapper;

    @Transactional
    public int doFollow(FollowDTO followDTO) {

//        long followingUserSeq = CustomUserUtils.getCurrentUserSeq();
        long followingUserSeq = 2L;

        // 팔로우하는 사람과 팔로우를 당하는 사람이 같다면 0을 리턴
        if (usersFollowDomainService.isSameFollowingAndFollowed(followDTO, followingUserSeq)) return 0;

        followDTO.setFollowingUserSeq(followingUserSeq);

        Follow newFollow = modelMapper.map(followDTO, Follow.class);

        boolean result = usersFollowDomainService.isExistFollow(newFollow);

        // 팔로우 관계라면 팔로우 취소한다.
        if (result) {
            usersFollowDomainService.deleteFollow(newFollow);
            return 1;
        }

        // 팔로우 관계가 아니라면 팔로우를 한다.
        usersFollowDomainService.save(newFollow);
        return 2;
    }


}
