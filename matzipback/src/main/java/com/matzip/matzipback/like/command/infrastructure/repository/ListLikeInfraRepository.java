package com.matzip.matzipback.like.command.infrastructure.repository;

import com.matzip.matzipback.like.command.domain.aggregate.Like;
import com.matzip.matzipback.like.command.domain.repository.ListLikeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListLikeInfraRepository extends JpaRepository<Like, Long>, ListLikeRepository {
}
