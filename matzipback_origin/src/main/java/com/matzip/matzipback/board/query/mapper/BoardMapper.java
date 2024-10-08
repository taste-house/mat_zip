package com.matzip.matzipback.board.query.mapper;

import com.matzip.matzipback.board.query.dto.BoardCategoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<BoardCategoryDTO> getBoardCategories();

    List<BoardCategoryDTO> getBoardFavorCategories(@Param("userSeq") long userSeq);
}
