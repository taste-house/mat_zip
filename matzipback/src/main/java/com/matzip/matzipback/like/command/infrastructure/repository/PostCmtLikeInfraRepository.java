package com.matzip.matzipback.like.command.infrastructure.repository;

import com.matzip.matzipback.like.command.domain.aggregate.Like;
import com.matzip.matzipback.like.command.domain.repository.PostCmtLikeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCmtLikeInfraRepository extends JpaRepository<Like, Long>, PostCmtLikeRepository {

}
