package com.matzip.matzipback.board.command.domain.repository;

import com.matzip.matzipback.board.command.domain.aggregate.Post;

import java.util.Optional;

public interface PostDomainRepository {

    Optional<Post> findById(Long postSeq);
}
