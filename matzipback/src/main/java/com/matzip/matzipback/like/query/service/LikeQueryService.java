package com.matzip.matzipback.like.query.service;

import com.matzip.matzipback.common.util.CustomUserUtils;
import com.matzip.matzipback.like.query.dto.LikedPostDTO;
import com.matzip.matzipback.like.query.mapper.LikeQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeQueryService {

    private final LikeQueryMapper likeQueryMapper;

    public Long findLikeByUserSeqAndPostCommentSeq(Long userSeq, Long postCommentSeq) {
        return likeQueryMapper.findLikeByUserSeqAndPostCommentSeq(userSeq, postCommentSeq);
    }

    public List<LikedPostDTO> findMyLikedPost() {
        long userSeq = CustomUserUtils.getCurrentUserSeq();
        return likeQueryMapper.findMyLikedPost(userSeq);
    }
}
