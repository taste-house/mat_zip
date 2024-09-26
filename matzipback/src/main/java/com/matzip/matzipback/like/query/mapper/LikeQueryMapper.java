package com.matzip.matzipback.like.query.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeQueryMapper {

    Long findLikeByUserSeqAndPostCommentSeq(@Param("likeUserSeq") Long userSeq, @Param("postCommentSeq") Long postCommentSeq);
}
