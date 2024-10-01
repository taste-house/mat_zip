package com.matzip.matzipback.like.command.domain.repository;

import com.matzip.matzipback.like.command.domain.aggregate.Like;

import java.util.Optional;

public interface ListCmtLikeRepository {

    Optional<Like> findByLikeUserSeqAndListCommentSeq(long likeUserSeq, Long listCommentSeq);

    void delete(Like foundListCmtLike);

    Like save(Like newListCmtLike);
}
