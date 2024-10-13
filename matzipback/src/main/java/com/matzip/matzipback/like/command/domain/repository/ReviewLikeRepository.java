package com.matzip.matzipback.like.command.domain.repository;

import com.matzip.matzipback.like.command.domain.aggregate.Like;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

public interface ReviewLikeRepository {

    Optional<Like> findByLikeUserSeqAndReviewSeq(Long likeUserSeq, Long reviewSeq);

    Like save(Like like);

    void deleteById(Long likeSeq);
}
