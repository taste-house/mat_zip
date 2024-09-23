package com.matzip.matzipback.board.command.domain.repository;

import com.matzip.matzipback.board.command.domain.aggregate.PostComment;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostCommentRepository {
    Optional<PostComment> save(PostComment postComment);
}
