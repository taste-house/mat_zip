package com.matzip.matzipback.board.command.domain.repository;

import com.matzip.matzipback.board.command.domain.aggregate.PostTag;

public interface PostTagRepository {
    PostTag save(PostTag postTag);
}
