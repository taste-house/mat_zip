package com.matzip.matzipuser.board.query.service;

import com.matzip.matzipuser.board.query.dto.BoardCategoryDTO;
import com.matzip.matzipuser.board.query.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardQueryService {

    private final BoardMapper boardMapper;


    public List<BoardCategoryDTO> getBoardCategories() {
        return boardMapper.getBoardCategories();
    }

    public List<BoardCategoryDTO> getBoardFavorCategories(String userSeq) {
        return boardMapper.getBoardFavorCategories(Long.parseLong(userSeq));
    }
}
