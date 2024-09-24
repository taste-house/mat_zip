package com.matzip.matzipback.board.command.infrastructure.repository;

import com.matzip.matzipback.board.command.domain.aggregate.Post;
import com.matzip.matzipback.board.command.domain.repository.PostDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostInfraRepository extends PostDomainRepository, JpaRepository<Post, Integer> {
}
