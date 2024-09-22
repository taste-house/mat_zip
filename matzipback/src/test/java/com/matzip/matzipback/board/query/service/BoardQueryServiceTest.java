package com.matzip.matzipback.board.query.service;

import com.matzip.matzipback.board.query.dto.BoardCategoryDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class BoardQueryServiceTest {

    @Autowired
    private BoardQueryService boardQueryService;

    @DisplayName("게시판 전체 목록 조회")
    @Test
    void getBoardCategory() {
        // when
        List<BoardCategoryDTO> boardCategoryList = boardQueryService.getBoardCategories();

        // then
        assertNotNull(boardCategoryList);
        assertTrue(boardCategoryList.size() > 0);
        assertThat(boardCategoryList).contains(new BoardCategoryDTO("dummy1"));
    }

}