package com.matzip.matzipuser.like.command.domain.repository;

import com.matzip.matzipuser.like.command.domain.aggregate.Like;

import java.util.Optional;

// 1차 수정완료 - 창윤
public interface PostLikeRepository {
    boolean existsByLikeUserSeqAndPostSeq(long likeUserSeq, Long postSeq);

    Like save(Like newPostLike);

    void deleteByLikeUserSeqAndPostSeq(long likeUserSeq, long postSeq);
}
