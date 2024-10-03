package com.matzip.matzipback.like.command.application.service;

import com.matzip.matzipback.like.command.application.dto.PostCmtLikeReqDTO;
import com.matzip.matzipback.like.command.domain.aggregate.Like;
import com.matzip.matzipback.like.command.domain.service.PostCmtLikeDomainService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostCmtLikeService {

    private final PostCmtLikeDomainService postCmtLikeDomainService;
    private final ModelMapper modelMapper;

    @Transactional
    public boolean savePostCmtLike(PostCmtLikeReqDTO postCmtLikeReqDTO) {

        long likeUserSeq = /*CustomUserUtils.getCurrentUserSeq()*/ 1L;

        Like foundPostCmtLike = postCmtLikeDomainService.findLikeByUserSeqAndPostCommentSeq(
                likeUserSeq,
                postCmtLikeReqDTO.getPostCommentSeq()
        ).orElse(null);

        // 좋아요를 하지 않은 게시물에 대한 경우
        if (foundPostCmtLike == null) {
            postCmtLikeReqDTO.setLikeUserSeq(likeUserSeq);
            Like newPostCmtLike = modelMapper.map(postCmtLikeReqDTO, Like.class); // 좋아요 저장
            postCmtLikeDomainService.save(newPostCmtLike);
            return true;
        } else {
            // 이미 좋아요를 한 게시물에 대한 경우
            postCmtLikeDomainService.deleteById(foundPostCmtLike.getLikeSeq()); // 좋아요를 다시 누르면 좋아요 취소됨
            return false;
        }

    }
}
