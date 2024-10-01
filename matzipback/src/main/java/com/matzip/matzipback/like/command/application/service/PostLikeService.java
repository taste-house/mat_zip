package com.matzip.matzipback.like.command.application.service;

import com.matzip.matzipback.like.command.application.dto.PostLikeReqDTO;
import com.matzip.matzipback.like.command.domain.aggregate.Like;
import com.matzip.matzipback.like.command.domain.repository.PostLikeRepository;
import com.matzip.matzipback.like.command.domain.service.PostLikeDomainService;
import com.matzip.matzipback.users.command.domain.service.UserActivityDomainService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeDomainService postLikeDomainService;
    private final UserActivityDomainService userActivityDomainService;
    private final ModelMapper modelMapper;

    @Transactional
    public Like savePostLike(PostLikeReqDTO postLikeReqDTO) {

//        Long likeUserSeq = CustomUserUtils.getCurrentUserSeq();
        long likeUserSeq = 2L;

        Like foundPostLike = postLikeDomainService.findByLikeUserSeqAndPostSeq(likeUserSeq, postLikeReqDTO.getPostSeq()).orElse(null);

        // 좋아요를 하지 않은 게시물에 대한 경우
        if (foundPostLike == null) {
            postLikeReqDTO.setLikeUserSeq(likeUserSeq);
            Like newPostLike = modelMapper.map(postLikeReqDTO, Like.class); // 좋아요 저장
            return postLikeDomainService.save(newPostLike);
        }

        // 좋아요를 한 게시물이 있는 경우
        postLikeDomainService.delete(foundPostLike); // 좋아요를 다시 누르면 좋아요 취소됨
        return null;

    }
}
