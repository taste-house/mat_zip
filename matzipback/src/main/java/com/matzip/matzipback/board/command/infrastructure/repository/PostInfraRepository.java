package com.matzip.matzipback.board.command.infrastructure.repository;

import com.matzip.matzipback.board.command.domain.aggregate.Post;
import com.matzip.matzipback.board.command.domain.repository.PostRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostInfraRepository extends PostRepository, JpaRepository<Post, Long> {
}
