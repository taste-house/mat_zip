package com.matzip.matzipback.like.command.domain.repository;

import com.matzip.matzipback.like.command.domain.aggregate.Like;

public interface PostCmtLikeRepository  {

    void deleteById(Long likeSeq);

    Like save(Like like);
}
