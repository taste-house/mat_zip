package com.matzip.matzipback.like.command.application.service;

import com.matzip.matzipback.like.command.application.dto.PostCmtLikeReqDTO;
import com.matzip.matzipback.like.command.domain.aggregate.Like;
import com.matzip.matzipback.like.command.domain.service.PostCmtLikeDomainService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostCmtLikeServiceTest {

    @Autowired
    private PostCmtLikeService postCmtLikeService;

    @Autowired
    private PostCmtLikeDomainService postCmtLikeDomainService;

    @DisplayName("게시물 댓글 좋아요")
    @Test
    void savePostCmtLikeTest() {
        // given
        PostCmtLikeReqDTO postCmtLikeReqDTO = new PostCmtLikeReqDTO();
        postCmtLikeReqDTO.setLikeUserSeq(1L);
        postCmtLikeReqDTO.setPostCommentSeq(15L);

        // when
        boolean result = postCmtLikeService.savePostCmtLike(postCmtLikeReqDTO);

        // then
        assertTrue(result); // 로직이 성공적으로 수행 됐는지 확인
        Optional<Like> foundLike = postCmtLikeDomainService.findLikeByUserSeqAndPostCommentSeq(
                postCmtLikeReqDTO.getLikeUserSeq(),
                postCmtLikeReqDTO.getPostCommentSeq()
        );
        assertThat(foundLike).isPresent();  // 좋아요가 저장되었는지 확인
        assertThat(foundLike.get()).isNotNull(); // 좋아요 객체가 null 이 아님을 확인

    }

    @DisplayName("게시글 댓글 좋아요 취소")
    @Test
    void deletePostCmtLikeTest() {
        // given
        PostCmtLikeReqDTO postCmtLikeReqDTO = new PostCmtLikeReqDTO();
        postCmtLikeReqDTO.setLikeUserSeq(1L);
        postCmtLikeReqDTO.setPostCommentSeq(2L);

        // when
        boolean result = postCmtLikeService.savePostCmtLike(postCmtLikeReqDTO);

        // then
        assertFalse(result); // 로직이 성공적으로 수행 됐는지 확인
        Optional<Like> foundLike = postCmtLikeDomainService.findLikeByUserSeqAndPostCommentSeq(
                postCmtLikeReqDTO.getLikeUserSeq(),
                postCmtLikeReqDTO.getPostCommentSeq()
        );
        assertThat(foundLike).isNotPresent();  // 좋아요가 삭제되었는지 확인
        assertThat(foundLike).isNotNull();  // 좋아요가 null 이 아님을 확인
    }

}