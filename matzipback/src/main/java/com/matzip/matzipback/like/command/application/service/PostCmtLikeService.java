package com.matzip.matzipback.like.command.application.service;

import com.matzip.matzipback.like.command.application.dto.PostCmtLikeReqDTO;
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

    // 2차 수정 완료 - 창윤
    @Transactional
    public boolean savePostCmtLike(PostCmtLikeReqDTO postCmtLikeReqDTO) {

        long likeUserSeq = /*CustomUserUtils.getCurrentUserSeq()*/ 1L;

        postCmtLikeReqDTO.setLikeUserSeq(likeUserSeq);

        boolean resultLike = postCmtLikeDomainService
                .existsByLikeUserSeqAndPostCommentSeq(postCmtLikeReqDTO);

        // 좋아요를 하지 않은 게시물에 대한 경우
        if (!resultLike) {

            postCmtLikeDomainService.save(postCmtLikeReqDTO);
            return true;
        } else {
            // 이미 좋아요를 한 게시물에 대한 경우
            postCmtLikeDomainService.deleteByLikeUserSeqAndPostCommentSeq(postCmtLikeReqDTO); // 좋아요를 다시 누르면 좋아요 취소됨
            return false;
        }

    }
}
