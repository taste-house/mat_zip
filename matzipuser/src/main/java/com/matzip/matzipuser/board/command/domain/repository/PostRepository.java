package com.matzip.matzipuser.board.command.domain.repository;

import com.matzip.matzipuser.board.command.domain.aggregate.Post;

import java.util.Optional;

public interface PostRepository {

    void deleteById(Long postSeq);

    Optional<Post> findById(Long postSeq);

    Post save(Post post);
}
