package com.matzip.matzipuser.like.command.domain.service;

import com.matzip.matzipuser.like.command.application.dto.ListCmtLikeReqDTO;
import com.matzip.matzipuser.like.command.domain.aggregate.Like;
import com.matzip.matzipuser.like.command.domain.repository.ListCmtLikeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListCmtLikeDomainService {

    private final ListCmtLikeRepository listCmtLikeRepository;
    private final ModelMapper modelMapper;


    // 리스트 댓글 좋아요 등록
    public void save(ListCmtLikeReqDTO listCmtLikeReqDTO) {
        Like newListCmtLike = modelMapper.map(listCmtLikeReqDTO, Like.class);
        listCmtLikeRepository.save(newListCmtLike);
    }

    // 리스트 댓글 좋아요 취소
    public void deleteByLikeUserSeqAndListCommentSeq(ListCmtLikeReqDTO listCmtLikeReqDTO) {
        listCmtLikeRepository.deleteByLikeUserSeqAndListCommentSeq(listCmtLikeReqDTO.getLikeUserSeq(), listCmtLikeReqDTO.getListCommentSeq());
    }

    // 리스트 댓글 존재하는지 확인
    public boolean existsByLikeUserSeqAndListCommentSeq(ListCmtLikeReqDTO listCmtLikeReqDTO) {
        return listCmtLikeRepository.existsByLikeUserSeqAndListCommentSeq(listCmtLikeReqDTO.getLikeUserSeq(), listCmtLikeReqDTO.getListCommentSeq());
    }
}
