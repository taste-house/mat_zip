package com.matzip.matzipback.board.command.application.service;

import com.matzip.matzipback.board.command.application.dto.ReqPostCmtUpdateDTO;
import com.matzip.matzipback.board.command.application.dto.ResPostCmtDTO;
import com.matzip.matzipback.board.command.application.dto.ReqPostCmtCreateDTO;
import com.matzip.matzipback.board.command.domain.aggregate.PostComment;
import com.matzip.matzipback.board.command.domain.repository.PostCommentRepository;
import com.matzip.matzipback.board.command.infrastructure.repository.PostCommentInfraRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostCommentService {

    private final PostCommentRepository postCommentRepository;
    private final PostCommentInfraRepository postCommentInfraRepository;

    // 댓글 작성
    @Transactional
    public ResPostCmtDTO createPostComment(ReqPostCmtCreateDTO reqPostCmtCreateDTO) {

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
        ResPostCmtDTO responseDTO = new ResPostCmtDTO();
        responseDTO.setPostSeq(savedPostComment.getPostSeq());

        return responseDTO;
    }

    // 댓글 수정
    @Transactional
    public ResPostCmtDTO updatePostComment(ReqPostCmtUpdateDTO reqPostCmtUpdateDTO) {

        // 나중에 Authorization 에서 빼와야한다. JwtUtil 에서의 메서드 활용할 것임
        Long userSeq = 1L;

        Long postCommentSeq = reqPostCmtUpdateDTO.getPostCommentSeq();

        // 스프링 jpa를 이용해서 영속성 컨텍스트로 해당 댓글 가져오기
        PostComment postComment = postCommentInfraRepository.findById(postCommentSeq)
                .orElseThrow(NoSuchElementException::new);

        // 댓글 Status가 inactive일 경우 예외 처리
//        String postCommentStatus = postComment.getPostCommentStatus();
//        if (postCommentStatus.equals("inactive")) {
//            throw new RuntimeException("삭제된 댓글입니다.");
//        }

        postComment.updatePostCmt(reqPostCmtUpdateDTO.getPostCommentContent());

        return new ResPostCmtDTO(postComment.getPostSeq());
    }





}
