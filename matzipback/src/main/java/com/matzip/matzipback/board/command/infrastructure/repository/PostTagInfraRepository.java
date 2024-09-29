package com.matzip.matzipback.board.command.infrastructure.repository;

import com.matzip.matzipback.board.command.domain.aggregate.PostTag;
import com.matzip.matzipback.board.command.domain.repository.PostTagRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagInfraRepository extends PostTagRepository, JpaRepository<PostTag, Long> {
}
