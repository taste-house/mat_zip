package com.matzip.matzipback.like.command.domain.service;

import com.matzip.matzipback.like.command.application.dto.ListLikeReqDTO;
import com.matzip.matzipback.like.command.domain.aggregate.Like;
import com.matzip.matzipback.like.command.domain.repository.ListLikeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListLikeDomainService {

    private final ListLikeRepository listLikeRepository;
    private final ModelMapper modelMapper;

    // 1차 수정 완료 - 창윤
    // 좋아요 등록
    public void save(ListLikeReqDTO listLikeReqDTO) {
        Like newListLike = modelMapper.map(listLikeReqDTO, Like.class);
        listLikeRepository.save(newListLike);
    }

    // 1차 수정 완료 - 창윤
    // 좋아요 기록이 있는지 조회
    public boolean existsByLikeUserSeqAndListSeq(ListLikeReqDTO listLikeReqDTO) {
        return listLikeRepository
                .existsByLikeUserSeqAndListSeq(
                        listLikeReqDTO.getLikeUserSeq(), listLikeReqDTO.getListSeq());
    }

    // 1차 수정 완료 - 창윤
    // 좋아요 취소
    public void deleteByLieUserSeqAndListSeq(ListLikeReqDTO listLikeReqDTO) {
        listLikeRepository
                .deleteByLieUserSeqAndListSeq(
                        listLikeReqDTO.getLikeUserSeq(), listLikeReqDTO.getListSeq());
    }
}
