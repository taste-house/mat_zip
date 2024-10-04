package com.matzip.matzipuser.like.command.application.service;

import com.matzip.matzipuser.common.util.CustomUserUtils;
import com.matzip.matzipuser.like.command.application.dto.PostLikeReqDTO;
import com.matzip.matzipuser.like.command.domain.service.PostLikeDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeDomainService postLikeDomainService;

    // 1차 수정완료 - 창윤
    @Transactional
    public boolean savePostLike(PostLikeReqDTO postLikeReqDTO) {

        Long likeUserSeq = CustomUserUtils.getCurrentUserSeq();
        postLikeReqDTO.setLikeUserSeq(likeUserSeq);

        // 존재하는지 검사
        boolean isExistsLike = postLikeDomainService
                .existsByLikeUserSeqAndPostSeq(postLikeReqDTO);

        // 좋아요를 하지 않은 게시물에 대한 경우
        if (!isExistsLike) {
            postLikeDomainService.save(postLikeReqDTO);
            return true;
        }

        // 좋아요를 한 게시물이 있는 경우
        postLikeDomainService
                .deleteByLikeUserSeqAndPostSeq(postLikeReqDTO); // 좋아요를 다시 누르면 좋아요 취소됨
        return false;
    }
}
