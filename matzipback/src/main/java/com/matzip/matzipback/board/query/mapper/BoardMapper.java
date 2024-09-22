package com.matzip.matzipback.board.query.mapper;

import com.matzip.matzipback.board.query.dto.BoardCategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    List<BoardCategoryDTO> getBoardCategories();
}
