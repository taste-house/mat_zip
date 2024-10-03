package com.matzip.matzipback.like.command.domain.service;

import com.matzip.matzipback.like.command.application.dto.PostCmtLikeReqDTO;
import com.matzip.matzipback.like.command.domain.aggregate.Like;
import com.matzip.matzipback.like.command.domain.repository.PostCmtLikeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCmtLikeDomainService {

    private final PostCmtLikeRepository postCmtLikeRepository;
    private final ModelMapper modelMapper;

    // 2차 수정 완료 - 창윤
    // 해당 게시글 댓글에 대해 좋아요를 했는지 조회
    public boolean existsByLikeUserSeqAndPostCommentSeq(PostCmtLikeReqDTO postCmtLikeReqDTO) {
        return postCmtLikeRepository
                .existsByLikeUserSeqAndPostCommentSeq(
                        postCmtLikeReqDTO.getLikeUserSeq(), postCmtLikeReqDTO.getPostCommentSeq());
    }

    // 2차 수정 완료 - 창윤
    // 좋아요 저장
    public void save(PostCmtLikeReqDTO postCmtLikeReqDTO) {
        Like newPostCmtLike = modelMapper.map(postCmtLikeReqDTO, Like.class);
        postCmtLikeRepository.save(newPostCmtLike);
    }

    // 2차 수정 완료 - 창윤
    // 좋아요 삭제
    public void deleteByLikeUserSeqAndPostCommentSeq(PostCmtLikeReqDTO postCmtLikeReqDTO) {
        postCmtLikeRepository
                .deleteByLikeUserSeqAndPostCommentSeq(
                        postCmtLikeReqDTO.getLikeUserSeq(), postCmtLikeReqDTO.getPostCommentSeq());
    }
}
