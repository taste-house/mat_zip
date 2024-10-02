package com.matzip.matzipback.like.command.domain.repository;

import com.matzip.matzipback.like.command.domain.aggregate.Like;

// 1차 수정 완료 - 창윤
public interface ListLikeRepository {

    Like save(Like newListLike);

    void deleteByLieUserSeqAndListSeq(long likeUserSeq, long listSeq);

    boolean existsByLikeUserSeqAndListSeq(long likeUserSeq, long listSeq);

}
