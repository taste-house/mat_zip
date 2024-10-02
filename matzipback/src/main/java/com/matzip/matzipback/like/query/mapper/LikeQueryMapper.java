package com.matzip.matzipback.like.query.mapper;

import com.matzip.matzipback.like.query.dto.LikedPostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LikeQueryMapper {

    // 필요한 메서드인지 생각할 필요가 있음
    // 1차 수정 미완료 - 창윤
    Long findLikeByUserSeqAndPostCommentSeq(@Param("likeUserSeq") Long userSeq, @Param("postCommentSeq") Long postCommentSeq);

    // 1차 수정 완료 - 창윤
    List<LikedPostDTO> findMyLikedPost(@Param("userSeq") Long userSeq,
                                       @Param("offset") long offset,
                                       @Param("size") int size);

    // 1차 수정 완료 - 창윤
    long countMyLikePost(@Param("userSeq") Long userSeq);
}
