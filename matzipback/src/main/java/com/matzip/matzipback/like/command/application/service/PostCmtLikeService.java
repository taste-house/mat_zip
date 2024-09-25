package com.matzip.matzipback.like.command.application.service;

import com.matzip.matzipback.like.command.domain.aggregate.Like;
import com.matzip.matzipback.like.command.domain.repository.PostCmtLikeRepository;
import com.matzip.matzipback.like.query.service.LikeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostCmtLikeService {

    private final PostCmtLikeRepository postCmtLikeRepository;
    private final LikeQueryService likeQueryService;

    @Transactional
    public void active(Long postCommentSeq) {

        Long userSeq = 1L;

        Long likeSeq = likeQueryService.findLikeByUserSeqAndPostCommentSeq(userSeq, postCommentSeq);

        if (likeSeq != null) {
            // 이미 좋아요를 누른 경우, 좋아요 삭제 로직
            postCmtLikeRepository.deleteById(likeSeq);
        } else {
            // 좋아요 추가 로직
            postCmtLikeRepository.save(Like.create(userSeq, postCommentSeq));
        }

    }
}
