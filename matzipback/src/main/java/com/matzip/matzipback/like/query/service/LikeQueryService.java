package com.matzip.matzipback.like.query.service;

import com.matzip.matzipback.like.query.mapper.LikeQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeQueryService {

    private LikeQueryMapper likeQueryMapper;

    public Long findLikeByUserSeqAndPostCommentSeq(Long userSeq, Long postCommentSeq) {
        return likeQueryMapper.findLikeByUserSeqAndPostCommentSeq(userSeq, postCommentSeq);
    }
}
