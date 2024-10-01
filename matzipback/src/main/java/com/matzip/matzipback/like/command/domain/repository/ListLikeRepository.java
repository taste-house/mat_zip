package com.matzip.matzipback.like.command.domain.repository;

import com.matzip.matzipback.like.command.domain.aggregate.Like;

import java.util.Optional;

public interface ListLikeRepository {

    Like save(Like newListLike);

    void delete(Like foundListLike);

    Optional<Like> findByLikeUserSeqAndListSeq(long likeUserSeq, Long listSeq);

}
