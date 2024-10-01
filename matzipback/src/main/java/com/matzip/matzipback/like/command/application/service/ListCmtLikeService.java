package com.matzip.matzipback.like.command.application.service;

import com.matzip.matzipback.like.command.application.dto.ListCmtLikeReqDTO;
import com.matzip.matzipback.like.command.domain.aggregate.Like;
import com.matzip.matzipback.like.command.domain.service.ListCmtLikeDomainService;
import com.matzip.matzipback.like.command.domain.service.ListLikeDomainService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListCmtLikeService {

    private final ListCmtLikeDomainService listCmtLikeDomainService;
    private final ModelMapper modelMapper;


    @Transactional
    public Like saveAndDeleteListCmtLike( ListCmtLikeReqDTO listCmtLikeRequest) {
        // 인가받은 유저 seq 받아오기
//        Long likeUserSeq = CustomUserUtils.getCurrentUserSeq();

        long likeUserSeq = 4L;

        Like foundListCmtLike = listCmtLikeDomainService.findLikeByLikeUserSeqAndListCmtSeq(likeUserSeq, listCmtLikeRequest.getListCommentSeq()).orElse(null);

        if(foundListCmtLike == null) {
            listCmtLikeRequest.setLikeUserSeq(likeUserSeq);
            Like newListCmtLike = modelMapper.map(listCmtLikeRequest, Like.class);
            return listCmtLikeDomainService.save(newListCmtLike);
        }

        listCmtLikeDomainService.delete(foundListCmtLike);
        return null;
    }
}
