package com.matzip.matzipuser.like.query.mapper;

import com.matzip.matzipuser.like.query.dto.LikedPostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface LikeQueryMapper {

    Long findLikeByUserSeqAndPostCommentSeq(@Param("likeUserSeq") Long userSeq, @Param("postCommentSeq") Long postCommentSeq);

    List<LikedPostDTO> findMyLikedPost(@Param("userSeq") Long userSeq);
}
