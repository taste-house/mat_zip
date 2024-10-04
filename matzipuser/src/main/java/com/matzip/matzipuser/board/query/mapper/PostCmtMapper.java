package com.matzip.matzipuser.board.query.mapper;

import com.matzip.matzipuser.board.query.dto.PostCommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostCmtMapper {

    List<PostCommentDTO> getPostCmtsByUserSeq(@Param("offset") int offset, @Param("size") Integer size, @Param("userSeq") Long userSeq);

    Long countPostCmtsByUserSeq(Long userSeq);

}
