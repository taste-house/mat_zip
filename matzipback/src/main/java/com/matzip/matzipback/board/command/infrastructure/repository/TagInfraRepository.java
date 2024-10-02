package com.matzip.matzipback.board.command.infrastructure.repository;

import com.matzip.matzipback.board.command.domain.repository.TagRepository;
import com.matzip.matzipback.board.command.domain.aggregate.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagInfraRepository extends TagRepository, JpaRepository<Tag, Long> {
}
