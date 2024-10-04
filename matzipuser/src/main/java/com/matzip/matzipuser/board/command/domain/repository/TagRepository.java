package com.matzip.matzipuser.board.command.domain.repository;

import com.matzip.matzipuser.board.command.domain.aggregate.Tag;

import java.util.Optional;

public interface TagRepository {
    Optional<Tag> findByTagName(String tagName);

    Tag save(Tag tag);
}
