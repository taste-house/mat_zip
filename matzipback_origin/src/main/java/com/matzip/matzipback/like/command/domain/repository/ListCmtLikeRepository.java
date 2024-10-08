package com.matzip.matzipback.like.command.domain.repository;

import com.matzip.matzipback.like.command.domain.aggregate.Like;

public interface ListCmtLikeRepository {

    boolean existsByLikeUserSeqAndListCommentSeq(long likeUserSeq, long listCommentSeq);

    void deleteByLikeUserSeqAndListCommentSeq(long likeUserSeq, long listCommentSeq);

    Like save(Like like);
}
