package com.matzip.matzipback.board.command.domain.repository;

import com.matzip.matzipback.board.command.domain.aggregate.PostTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface PostTagRepository {

    PostTag save(PostTag postTag);

    @Modifying
    @Query("DELETE FROM PostTag pt WHERE pt.postSeq = :seq")
    void deleteAllByPostSeq(@Param("seq") Long seq);
}
