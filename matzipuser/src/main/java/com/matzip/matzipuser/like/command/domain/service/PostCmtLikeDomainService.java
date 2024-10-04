package com.matzip.matzipuser.like.command.domain.service;

import com.matzip.matzipuser.exception.ErrorCode;
import com.matzip.matzipuser.exception.RestApiException;
import com.matzip.matzipuser.like.command.domain.aggregate.Like;
import com.matzip.matzipuser.like.command.domain.repository.PostCmtLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostCmtLikeDomainService {

    private final PostCmtLikeRepository postCmtLikeRepository;

    // 해당 게시글 댓글에 대해 좋아요를 했는지 조회
    public Optional<Like> findLikeByUserSeqAndPostCommentSeq(long likeUserSeq, Long postCommentSeq) {
        return postCmtLikeRepository.findByLikeUserSeqAndPostCommentSeq(likeUserSeq, postCommentSeq);
    }

    // 좋아요 저장
    public void save(Like newPostCmtLike) {
        postCmtLikeRepository.save(newPostCmtLike);
    }

    // 좋아요 삭제
    public void deleteById(Long likeSeq) {
        postCmtLikeRepository.deleteById(likeSeq);
    }
}
