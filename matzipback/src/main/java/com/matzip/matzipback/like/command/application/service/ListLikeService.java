package com.matzip.matzipback.like.command.application.service;

import com.matzip.matzipback.like.command.application.dto.ListLikeReqDTO;
import com.matzip.matzipback.like.command.domain.service.ListLikeDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ListLikeService {

    private final ListLikeDomainService listLikeDomainService;

    // 1차 수정완료 - 창윤
    @Transactional
    public boolean saveAndDeleteListLike(ListLikeReqDTO listLikeReqDTO) {


        // 인가받은 유저 seq 받아오기
//        Long likeUserSeq = CustomUserUtils.getCurrentUserSeq();

        long likeUserSeq = 4L;

        listLikeReqDTO.setLikeUserSeq(likeUserSeq);

        // 해당 리스트에 대한 좋아요가 존재하지는 확인
        boolean isLikeExists = listLikeDomainService
                .existsByLikeUserSeqAndListSeq(listLikeReqDTO);

        // 리스트 좋아요가 존재하지 않으면 좋아요 등록
        if (!isLikeExists) {
            listLikeDomainService.save(listLikeReqDTO);
            return true;
        }

        // 리스트 좋아요가 존재하면 좋아요 취소
        listLikeDomainService.deleteByLieUserSeqAndListSeq(listLikeReqDTO);
        return false;
    }
}
