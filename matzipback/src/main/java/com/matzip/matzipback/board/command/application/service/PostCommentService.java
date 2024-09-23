package com.matzip.matzipback.board.command.application.service;

import com.matzip.matzipback.board.command.application.dto.ResponsePostCommentDTO;
import com.matzip.matzipback.board.command.application.dto.RequestPostCommentDTO;
import com.matzip.matzipback.board.command.domain.aggregate.PostComment;
import com.matzip.matzipback.board.command.domain.repository.PostCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostCommentService {

    private final PostCommentRepository postCommentRepository;

    @Transactional
    public ResponsePostCommentDTO createPostComment(RequestPostCommentDTO requestPostCommentDTO) {

        // 나중에 Authorization 에서 빼와야한다. JwtUtil 에서의 메서드 활용할 것임
        Long userSeq = 1L;

        PostComment postComment = PostComment.create(requestPostCommentDTO, userSeq);

        // 댓글 저장
        Optional<PostComment> optionalSavedPC = postCommentRepository.save(postComment);

        // null 체크 및 처리
        if (!optionalSavedPC.isPresent()) {
            throw new RuntimeException("댓글 저장에 실패했습니다."); // 예외 처리
        }
        PostComment savedPostComment = optionalSavedPC.get();

        // DTO로 변환하여 반환
        ResponsePostCommentDTO responseDTO = new ResponsePostCommentDTO();
        responseDTO.setPostSeq(savedPostComment.getPostSeq());

        return responseDTO;
    }
}
