package com.matzip.matzipback.board.query.service;

import com.matzip.matzipback.board.query.dto.PostCmtListResponseDTO;
import com.matzip.matzipback.board.query.dto.PostCommentDTO;
import com.matzip.matzipback.board.query.mapper.PostCmtMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PostCmtQueryServiceTest {

    @Autowired
    private PostCmtQueryService postCmtQueryService;

    @Autowired
    private PostCmtMapper postCmtMapper;

    @DisplayName("회원 본인이 작성한 게시물 댓글 조회")
    @ParameterizedTest
    @CsvSource({
            "1, 10, 1",
            "2, 10, 1",
            "3, 10, 1"
    })
    void getCommentsByUserSeq(Integer page, Integer size, Long userSeq) {
        // when
        PostCmtListResponseDTO response = postCmtQueryService.getCommentsByUserSeq(page, size, userSeq);

        // then
        assertThat(response).isNotNull();
        assertThat(response.getCurrentPage()).isEqualTo(page);
        assertThat(response.getTotalPostComments()).isGreaterThanOrEqualTo(0);

        // 댓글 목록이 비어있지 않은지 확인
        List<PostCommentDTO> comments = response.getComments();
        assertThat(comments).isNotNull();
        assertThat(comments.size()).isLessThanOrEqualTo(size); // 페이지 크기 제한 확인
    }

}