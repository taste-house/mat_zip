package com.matzip.matzipuser.board.query.mapper;

import com.matzip.matzipuser.board.query.dto.BoardCategoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<BoardCategoryDTO> getBoardCategories();

    List<BoardCategoryDTO> getBoardFavorCategories(@Param("userSeq") long userSeq);
}
