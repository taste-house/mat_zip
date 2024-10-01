package com.matzip.matzipback.board.command.domain.repository;

import com.matzip.matzipback.board.command.domain.aggregate.PostComment;

import java.util.Optional;

public interface PostCommentRepository {

    void save(PostComment postComment);

    Optional<PostComment> findById(Long postCommentSeq);

    void deleteById(Long postCommentSeq);
}
