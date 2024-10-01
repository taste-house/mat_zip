package com.matzip.matzipback.board.command.application.service;

import com.matzip.matzipback.board.command.application.dto.ReqPostCmtCreateDTO;
import com.matzip.matzipback.board.command.application.dto.ReqPostCmtUpdateDTO;
import com.matzip.matzipback.board.command.application.dto.ResPostCmtDTO;
import com.matzip.matzipback.board.command.domain.repository.PostCommentRepository;
import com.matzip.matzipback.board.command.domain.service.PostCommentDomainService;
import com.matzip.matzipback.exception.ErrorCode;
import com.matzip.matzipback.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostCommentService {

    private final PostCommentRepository postCommentRepository;
    private final PostCommentDomainService postCommentDomainService;

    // 댓글 작성
    @Transactional
    public ResPostCmtDTO createPostComment(ReqPostCmtCreateDTO reqPostCmtCreateDTO) {
        return postCommentDomainService.save(reqPostCmtCreateDTO);
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
