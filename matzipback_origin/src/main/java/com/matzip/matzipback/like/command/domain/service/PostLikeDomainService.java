package com.matzip.matzipback.like.command.domain.service;

import com.matzip.matzipback.like.command.application.dto.PostLikeReqDTO;
import com.matzip.matzipback.like.command.domain.aggregate.Like;
import com.matzip.matzipback.like.command.domain.repository.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostLikeDomainService {

    private final PostLikeRepository postLikeRepository;
    private final ModelMapper modelMapper;


    // 1차 수정완료 - 창윤
    // 해당 게시글에 대한 좋아요를 했는지 조회
    public boolean existsByLikeUserSeqAndPostSeq(PostLikeReqDTO postLikeReqDTO) {
        return postLikeRepository
                .existsByLikeUserSeqAndPostSeq(
                        postLikeReqDTO.getLikeUserSeq(), postLikeReqDTO.getPostSeq());
    }

    // 1차 수정 완료 - 창윤
    // 좋아요 취소
    public void deleteByLikeUserSeqAndPostSeq(PostLikeReqDTO postLikeReqDTO) {
        postLikeRepository
                .deleteByLikeUserSeqAndPostSeq(
                        postLikeReqDTO.getLikeUserSeq(), postLikeReqDTO.getPostSeq());
    }

    // 1차 수정 완료 - 창윤
    // 좋아요 저장
    public void save(PostLikeReqDTO postLikeReqDTO) {
        Like newPostLike = modelMapper.map(postLikeReqDTO, Like.class); // 좋아요 저장
        postLikeRepository.save(newPostLike);
    }
}
