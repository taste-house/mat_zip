package com.matzip.matzipback.board.command.domain.service;

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
