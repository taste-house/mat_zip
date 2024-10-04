package com.matzip.matzipuser.like.command.infrastructure.repository;

import com.matzip.matzipuser.like.command.domain.aggregate.Like;
import com.matzip.matzipuser.like.command.domain.repository.PostCmtLikeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostCmtLikeInfraRepository extends JpaRepository<Like, Long>, PostCmtLikeRepository {
    Optional<Like> findByLikeUserSeqAndPostCommentSeq(long likeUserSeq, Long postCommentSeq);
}
