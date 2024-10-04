package com.matzip.matzipuser.board.command.infrastructure.repository;

import com.matzip.matzipuser.board.command.domain.aggregate.PostTag;
import com.matzip.matzipuser.board.command.domain.repository.PostTagRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagInfraRepository extends PostTagRepository, JpaRepository<PostTag, Long> {
}
