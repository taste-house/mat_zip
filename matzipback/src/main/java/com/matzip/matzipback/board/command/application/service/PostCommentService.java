package com.matzip.matzipback.board.command.application.service;

import com.matzip.matzipback.board.command.application.dto.ReqPostCmtCreateDTO;
import com.matzip.matzipback.board.command.application.dto.ReqPostCmtUpdateDTO;
import com.matzip.matzipback.board.command.application.dto.ResPostCmtDTO;
import com.matzip.matzipback.board.command.domain.aggregate.PostComment;
import com.matzip.matzipback.board.command.domain.repository.PostCommentRepository;
import com.matzip.matzipback.board.command.infrastructure.repository.PostCommentInfraRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostCommentService {

    private final PostCommentRepository postCommentRepository;
    private final PostCommentInfraRepository postCommentInfraRepository;
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
            throw new RuntimeException("댓글 저장에 실패했습니다."); // 예외 처리
        }

        return postCommentRepository.save(postComment); // 댓글 저장 후 Entity 반환
    }

    // 댓글 수정
    @Transactional
    public PostComment updatePostComment(ReqPostCmtUpdateDTO reqPostCmtUpdateDTO) {

        // 나중에 Authorization 에서 빼와야한다. JwtUtil 에서의 메서드 활용할 것임
        Long userSeq = 1L;

        Long postCommentSeq = reqPostCmtUpdateDTO.getPostCommentSeq();

        // 스프링 jpa를 이용해서 영속성 컨텍스트로 해당 댓글 가져오기
        PostComment postComment = postCommentRepository.findById(postCommentSeq)
                .orElseThrow(NoSuchElementException::new);

        // DB내 작성된 댓글 수정 (수정 전 입력 값과 동일하면 안된다.)
        if (postComment.getPostCommentContent().equals(reqPostCmtUpdateDTO.getPostCommentContent())) {
            throw new RuntimeException("댓글 수정에 실패했습니다."); // 예외 처리
        }

        // postCommentRepository.save(postComment);    // save 호출 시 @LastModifiedDate 가 적용

        // 수정 값이 자동으로 업데이트 시에 @LastModifiedDate 가 적용
        return postComment.updatePostCmt(reqPostCmtUpdateDTO.getPostCommentContent());

    }

    // 댓글 삭제(소프트 삭제)
    @Transactional
    public PostComment deletePostComment(Long postCommentSeq) {

        // 나중에 Authorization 에서 빼와야한다. JwtUtil 에서의 메서드 활용할 것임
        Long userSeq = 1L;

        // 스프링 jpa를 이용해서 영속성 컨텍스트로 해당 댓글 가져오기
        PostComment postComment = postCommentRepository.findById(postCommentSeq)
                .orElseThrow(NoSuchElementException::new);

        postCommentInfraRepository.delete(postComment);

        return postComment;
    }
}
