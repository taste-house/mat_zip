package com.matzip.matzipback.board.command.application.service;

import com.matzip.matzipback.board.command.application.dto.ResPostCmtCreateDTO;
import com.matzip.matzipback.board.command.application.dto.ReqPostCmtCreateDTO;
import com.matzip.matzipback.board.command.domain.aggregate.PostComment;
import com.matzip.matzipback.board.command.domain.repository.PostCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostCommentService {

    private final PostCommentRepository postCommentRepository;

    // 댓글 작성
    @Transactional
    public ResPostCmtCreateDTO createPostComment(ReqPostCmtCreateDTO reqPostCmtCreateDTO) {

        // 나중에 Authorization 에서 빼와야한다. JwtUtil 에서의 메서드 활용할 것임
        Long userSeq = 1L;

        PostComment postComment = PostComment.create(reqPostCmtCreateDTO, userSeq);

        // 댓글 저장
        PostComment savedPostComment = postCommentRepository.save(postComment);

        // null 체크 및 처리
        if (savedPostComment == null) {
            throw new RuntimeException("댓글 저장에 실패했습니다."); // 예외 처리
        }

        // DTO로 변환하여 반환
        ResPostCmtCreateDTO responseDTO = new ResPostCmtCreateDTO();
        responseDTO.setPostSeq(savedPostComment.getPostSeq());

        return responseDTO;
    }

    // 댓글 수정

}
