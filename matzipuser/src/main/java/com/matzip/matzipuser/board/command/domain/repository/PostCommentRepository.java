package com.matzip.matzipuser.board.command.domain.repository;

import com.matzip.matzipuser.board.command.domain.aggregate.PostComment;

import java.util.Optional;

public interface PostCommentRepository {

    PostComment save(PostComment postComment);

    Optional<PostComment> findById(Long postCommentSeq);

    void deleteById(Long postCommentSeq);
}
