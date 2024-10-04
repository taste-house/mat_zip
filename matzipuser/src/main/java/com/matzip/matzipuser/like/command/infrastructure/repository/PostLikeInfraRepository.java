package com.matzip.matzipuser.like.command.infrastructure.repository;

import com.matzip.matzipuser.like.command.domain.aggregate.Like;
import com.matzip.matzipuser.like.command.domain.repository.PostLikeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeInfraRepository extends JpaRepository<Like, Long>, PostLikeRepository {
}
