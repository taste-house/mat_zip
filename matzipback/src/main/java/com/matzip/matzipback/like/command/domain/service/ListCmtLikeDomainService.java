package com.matzip.matzipback.like.command.domain.service;

import com.matzip.matzipback.like.command.domain.aggregate.Like;
import com.matzip.matzipback.like.command.domain.repository.ListCmtLikeRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListCmtLikeDomainService {

    private final ListCmtLikeRepository listCmtLikeRepository;

    public Optional<Like> findLikeByLikeUserSeqAndListCmtSeq(long likeUserSeq, Long listCommentSeq) {
        return listCmtLikeRepository.findByLikeUserSeqAndListCommentSeq(likeUserSeq, listCommentSeq);
    }

    // 좋아요 취소
    public void delete(Like foundListCmtLike) {
        listCmtLikeRepository.delete(foundListCmtLike);
    }

    public Like save(Like newListCmtLike) {
        return listCmtLikeRepository.save(newListCmtLike);
    }
}
