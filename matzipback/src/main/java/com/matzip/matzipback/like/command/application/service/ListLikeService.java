package com.matzip.matzipback.like.command.application.service;

import com.matzip.matzipback.common.util.CustomUserUtils;
import com.matzip.matzipback.like.command.application.dto.ListLikeReqDTO;
import com.matzip.matzipback.like.command.domain.aggregate.Like;
import com.matzip.matzipback.like.command.domain.service.ListLikeDomainService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ListLikeService {

    private final ListLikeDomainService listLikeDomainService;
    private final ModelMapper modelMapper;

    @Transactional
    public Like saveAndDeleteListLike(ListLikeReqDTO listLikeRequest) {


        // 인가받은 유저 seq 받아오기
//        Long likeUserSeq = CustomUserUtils.getCurrentUserSeq();

        long likeUserSeq = 4L;

        Like foundListLike = listLikeDomainService.findByLikeUserSeqAndListSeq(likeUserSeq, listLikeRequest.getListSeq()).orElse(null);

        if (foundListLike == null) {
            listLikeRequest.setLikeUserSeq(likeUserSeq);
            Like newListLike = modelMapper.map(listLikeRequest, Like.class);
            return listLikeDomainService.save(newListLike);
        }

        listLikeDomainService.delete(foundListLike);
        return null;
    }
}
