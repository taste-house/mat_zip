package com.matzip.matzipuser.like.command.domain.repository;

import com.matzip.matzipuser.like.command.domain.aggregate.Like;

import java.util.Optional;

public interface PostCmtLikeRepository  {

    Optional<Like> findByLikeUserSeqAndPostCommentSeq(long likeUserSeq, Long postCommentSeq);

    Like save(Like newPostCmtLike);

    void deleteById(Long likeSeq);
}
