package com.matzip.matzipback.like.command.infrastructure.repository;

import com.matzip.matzipback.like.command.domain.aggregate.Like;
import com.matzip.matzipback.like.command.domain.repository.ReviewLikeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewLikeInfraRepository extends JpaRepository<Like, Long>, ReviewLikeRepository {
}
