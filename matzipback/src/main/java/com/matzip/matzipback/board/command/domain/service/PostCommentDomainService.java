package com.matzip.matzipback.board.command.domain.service;

import com.matzip.matzipback.board.command.application.dto.ReqPostCmtUpdateDTO;
import com.matzip.matzipback.board.command.domain.aggregate.PostComment;
import com.matzip.matzipback.board.command.domain.repository.PostCommentRepository;
import com.matzip.matzipback.exception.ErrorCode;
import com.matzip.matzipback.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostCommentDomainService {

    private final PostCommentRepository postCommentRepository;

    // postCommentSeq 로 postComment 조회
    public PostComment findByPostCommentSeq(Long postCommentSeq) {
        return postCommentRepository.findById(postCommentSeq)
                .orElseThrow(NoSuchElementException::new);
    }

    // 댓글 수정 후 저장
    public void updatePostCmt(ReqPostCmtUpdateDTO reqPostCmtUpdateDTO) {
        // 스프링 jpa를 이용해서 영속성 컨텍스트로 해당 댓글 가져오기
        PostComment postComment = postCommentRepository.findById(reqPostCmtUpdateDTO.getPostCommentSeq())
                .orElseThrow(() -> new RestApiException(ErrorCode.NOT_FOUND));

        // 수정 값이 업데이트 시에 자동으로 @LastModifiedDate 적용
        postComment.updatePostCmt(reqPostCmtUpdateDTO);
    }

    // postCommentSeq 로 조회 후 작성자 userSeq 로 반환
    public Long findUserSeqByPostCmtSeq(Long postCommentSeq) {
        PostComment postComment = postCommentRepository.findById(postCommentSeq)
                .orElseThrow(() -> new RestApiException(ErrorCode.NOT_FOUND));
        return postComment.getPostCommentUserSeq();
    }

    // postCommentSeq 로 deletebById
    public void deletePostCmtById(Long postCommentSeq) {
        postCommentRepository.deleteById(postCommentSeq);
    }


}
