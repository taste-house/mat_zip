package com.matzip.matzipuser.like.command.application.service;

import com.matzip.matzipuser.like.command.application.dto.ListCmtLikeReqDTO;
import com.matzip.matzipuser.like.command.domain.aggregate.Like;
import com.matzip.matzipuser.like.command.domain.service.ListCmtLikeDomainService;
import com.matzip.matzipuser.like.command.domain.service.ListLikeDomainService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListCmtLikeService {

    private final ListCmtLikeDomainService listCmtLikeDomainService;


    @Transactional
    public boolean saveAndDeleteListCmtLike(ListCmtLikeReqDTO listCmtLikeReqDTO) {
        // 인가받은 유저 seq 받아오기
//        Long likeUserSeq = CustomUserUtils.getCurrentUserSeq();

        long likeUserSeq = 4L;

        listCmtLikeReqDTO.setLikeUserSeq(likeUserSeq);

        boolean isLikeExists = listCmtLikeDomainService
                .existsByLikeUserSeqAndListCommentSeq(listCmtLikeReqDTO);

        // 리스트 댓글이 좋아요 안되어 있으면 좋아요 등록
        if(!isLikeExists) {
            listCmtLikeDomainService.save(listCmtLikeReqDTO);
            return true;
        }

        // 리스트 댓글이 좋아요되어 있으면 좋아요 취소
        listCmtLikeDomainService.deleteByLikeUserSeqAndListCommentSeq(listCmtLikeReqDTO);
        return false;
    }
}
