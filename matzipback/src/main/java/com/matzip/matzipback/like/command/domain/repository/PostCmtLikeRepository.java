package com.matzip.matzipback.like.command.domain.repository;

import com.matzip.matzipback.like.command.domain.aggregate.Like;

public interface PostCmtLikeRepository  {

    boolean existsByLikeUserSeqAndPostCommentSeq(long likeUserSeq, Long postCommentSeq);

    Like save(Like newPostCmtLike);

    void deleteByLikeUserSeqAndPostCommentSeq(long likeUserSeq, Long postCommentSeq);
}
