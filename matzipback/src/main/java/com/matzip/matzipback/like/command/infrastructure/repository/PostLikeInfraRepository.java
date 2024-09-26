package com.matzip.matzipback.like.command.infrastructure.repository;

import com.matzip.matzipback.like.command.domain.aggregate.Like;
import com.matzip.matzipback.like.command.domain.repository.PostLikeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeInfraRepository extends JpaRepository<Like, Long>, PostLikeRepository {
    Optional<Like> findByLikeUserSeqAndPostSeq(long likeUserSeq, Long postSeq);
}
