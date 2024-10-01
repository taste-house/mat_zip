package com.matzip.matzipback.board.command.application.service;

import com.matzip.matzipback.board.command.application.dto.ReqPostCmtCreateDTO;
import com.matzip.matzipback.board.command.application.dto.ReqPostCmtUpdateDTO;
import com.matzip.matzipback.board.command.domain.aggregate.PostComment;
import com.matzip.matzipback.board.command.domain.repository.PostCommentRepository;
import com.matzip.matzipback.board.command.domain.service.PostCommentDomainService;
import com.matzip.matzipback.exception.ErrorCode;
import com.matzip.matzipback.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostCommentService {

    private final PostCommentRepository postCommentRepository;
    private final PostCommentDomainService postCommentDomainService;
    private final ModelMapper modelMapper;

    // 댓글 작성
    @Transactional
    public PostComment createPostComment(ReqPostCmtCreateDTO reqPostCmtCreateDTO) {
        // 나중에 Authorization 에서 빼와야한다. JwtUtil 에서의 메서드 활용할 것임
        Long userSeq = 1L;

        // DTO -> Entity 매퍼를 사용하여 생성되도록 수정 (Entity 불변성 유지)
//        PostComment postComment = PostComment.create(reqPostCmtCreateDTO, userSeq);
        reqPostCmtCreateDTO.setPostCommentUserSeq(userSeq);
        PostComment postComment = modelMapper.map(reqPostCmtCreateDTO, PostComment.class);

        // 유효성 검사 (댓글 내용이 작성되어있어야 한다.)
        // null 체크 및 처리
        if (postComment.getPostCommentContent() == null || postComment.getPostCommentContent().isEmpty()) {
//            throw new RuntimeException("댓글 저장에 실패했습니다."); // 예외 처리
            return postComment;
        }

        return postCommentRepository.save(postComment); // 댓글 저장 후 Entity 반환
    }

    // 댓글 수정
    @Transactional
    public void updatePostComment(ReqPostCmtUpdateDTO reqPostCmtUpdateDTO) {

        // 나중에 Authorization 에서 빼와야한다. JwtUtil 에서의 메서드 활용할 것임
        Long userSeq = /*CustomUserUtils.getCurrentUserSeq();*/ 1L;

        Long resultPostCmtUserSeq
                = postCommentDomainService.findUserSeqByPostCmtSeq(reqPostCmtUpdateDTO.getPostCommentSeq());

        if (userSeq.equals(resultPostCmtUserSeq)) {
            postCommentDomainService.updatePostCmt(reqPostCmtUpdateDTO);
        }
    }

    // 댓글 삭제(소프트 삭제)
    @Transactional
    public void deletePostComment(Long postCommentSeq) {
        // 나중에 Authorization 에서 빼와야한다. JwtUtil 에서의 메서드 활용할 것임
        Long userSeq = /*CustomUserUtils.getCurrentUserSeq();*/ 1L;

        Long resultPostCmtUserSeq
                = postCommentDomainService.findUserSeqByPostCmtSeq(postCommentSeq);

        if (userSeq.equals(resultPostCmtUserSeq)) {
            postCommentDomainService.deletePostCmtById(postCommentSeq);
        } else {
            throw new RestApiException(ErrorCode.FORBIDDEN_ACCESS);
        }

    }
}
