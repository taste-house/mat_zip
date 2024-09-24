package com.matzip.matzipback.board.command.domain.repository;

import com.matzip.matzipback.board.command.domain.aggregate.PostComment;

public interface PostCommentRepository {
    PostComment save(PostComment postComment);
}
