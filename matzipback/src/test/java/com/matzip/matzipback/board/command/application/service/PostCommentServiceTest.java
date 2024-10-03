package com.matzip.matzipback.board.command.application.service;

import com.matzip.matzipback.board.command.application.dto.ReqPostCmtCreateDTO;
import com.matzip.matzipback.board.command.application.dto.ReqPostCmtUpdateDTO;
import com.matzip.matzipback.board.command.domain.aggregate.PostComment;
import com.matzip.matzipback.board.command.domain.service.PostCommentDomainService;
import com.matzip.matzipback.board.command.infrastructure.repository.PostCommentInfraRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional  // 테스트 후 DB에 반영되지 않고 롤백
class PostCommentServiceTest {

    @Autowired
    private PostCommentService postCommentService;

    @Autowired
    private PostCommentDomainService postCommentDomainService;

    @Autowired
    private PostCommentInfraRepository postCommentInfraRepository;


    // 가장 최근에 작성된 댓글을 반환하는 메서드
    private PostComment getMostRecentPostCmt() {
        return postCommentInfraRepository.findTopByOrderByPostCommentSeqDesc();
    }

    // 소프트 삭제된 항목 조회
    private PostComment getDeletedPostCmtById(Long postCommentSeq) {
        return postCommentInfraRepository.findByIdIncludingDeleted(postCommentSeq);
    }

    @DisplayName("댓글 작성 테스트")
    @Test
    void createPostCommentTest() {
        // given
        ReqPostCmtCreateDTO reqPostCmtCreateDTO = new ReqPostCmtCreateDTO();
        // 필요한 필드 설정
        reqPostCmtCreateDTO.setPostCommentUserSeq(/*CustomUserUtils.getCurrentUserSeq();*/ 1L);
        reqPostCmtCreateDTO.setPostSeq(1L);
        reqPostCmtCreateDTO.setPostCommentContent("테스트 코드 - 댓글 작성 1");

        // when
        postCommentService.createPostComment(reqPostCmtCreateDTO);

        //then
        // 댓글이 잘 저장되었는지 확인
        Long savedPostCommentSeq = getMostRecentPostCmt().getPostCommentSeq();
        assertNotNull(savedPostCommentSeq);
        assertNotNull(postCommentDomainService.findByPostCommentSeq(savedPostCommentSeq));

    }

    @DisplayName("댓글 수정 테스트")
    @Test
    void updatePostCommentTest() {
        // given
        ReqPostCmtUpdateDTO reqPostCmtUpdateDTO = new ReqPostCmtUpdateDTO();
        // 필요한 필드 설정
        reqPostCmtUpdateDTO.setPostCommentSeq(15L);
        reqPostCmtUpdateDTO.setPostCommentContent("테스트 코드 - 댓글 수정 1");

        // when
        postCommentService.updatePostComment(reqPostCmtUpdateDTO);

        // then
        PostComment updatedPostCmt = postCommentDomainService.findByPostCommentSeq(15L);
        assertThat(updatedPostCmt.getPostCommentContent()).isEqualTo("테스트 코드 - 댓글 수정 1");
    }

    @DisplayName("댓글 삭제 테스트")
    @Test
    void deletePostCommentTest() {
        // given
        Long postCommentSeq = 15L;

        // when
        postCommentService.deletePostComment(postCommentSeq);

        // then
        PostComment deletedPostCmt = getDeletedPostCmtById(postCommentSeq);
        assertThat(deletedPostCmt).isNotNull(); // 소프트 삭제이기 때문에 댓글은 존재하기에 null 이 아니어야 한다.
        assertThat(deletedPostCmt.getPostCommentStatus()).isEqualTo("delete");
    }
}