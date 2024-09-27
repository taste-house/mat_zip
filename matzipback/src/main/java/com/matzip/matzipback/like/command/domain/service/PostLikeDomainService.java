package com.matzip.matzipback.like.command.domain.service;

import com.matzip.matzipback.like.command.domain.aggregate.Like;
import com.matzip.matzipback.like.command.domain.repository.PostLikeRepository;
import com.matzip.matzipback.like.command.infrastructure.repository.PostLikeInfraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostLikeDomainService {

    private final PostLikeRepository postLikeRepository;

    // 해당 게시글에 대한 좋아요를 했는지 조회
    public Optional<Like> findByLikeUserSeqAndPostSeq(long likeUserSeq, Long postSeq) {
        return postLikeRepository.findByLikeUserSeqAndPostSeq(likeUserSeq, postSeq);
    }

    // 좋아요 취소
    public void delete(Like foundPostLike) {
        postLikeRepository.delete(foundPostLike);
    }

    // 좋아요 저장
    public Like save(Like newPostLike) {
        return postLikeRepository.save(newPostLike);
    }
}
