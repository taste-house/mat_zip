package com.matzip.matzipback.like.command.domain.service;

import com.matzip.matzipback.like.command.domain.aggregate.Like;
import com.matzip.matzipback.like.command.domain.repository.ListLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListLikeDomainService {

    private final ListLikeRepository listLikeRepository;

    // 좋아요 등록
    public Like save(Like newListLike) {
        return listLikeRepository.save(newListLike);
    }

    // 좋아요 기록이 있는지 조회
    public Optional<Like> findByLikeUserSeqAndListSeq(long likeUserSeq, Long listSeq) {
        return listLikeRepository.findByLikeUserSeqAndListSeq(likeUserSeq, listSeq);
    }

    // 좋아요 취소
    public void delete(Like foundListLike) {
        listLikeRepository.delete(foundListLike);
    }
}
