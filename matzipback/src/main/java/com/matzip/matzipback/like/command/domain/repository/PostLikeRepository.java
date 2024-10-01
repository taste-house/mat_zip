package com.matzip.matzipback.like.command.domain.repository;

import com.matzip.matzipback.like.command.domain.aggregate.Like;

import java.util.Optional;

public interface PostLikeRepository {
    Optional<Like> findByLikeUserSeqAndPostSeq(long likeUserSeq, Long postSeq);

    Like save(Like newPostLike);

    void delete(Like foundPostLike);
}
