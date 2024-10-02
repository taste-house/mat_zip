package com.matzip.matzipback.matzipList.command.domain.service;

import com.matzip.matzipback.exception.ErrorCode;
import com.matzip.matzipback.exception.RestApiException;
import com.matzip.matzipback.matzipList.command.application.dto.CreateListCmtRequest;
import com.matzip.matzipback.matzipList.command.application.dto.UpdateListCmtRequest;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyListComment;
import com.matzip.matzipback.matzipList.command.domain.repository.ListCmtDomainRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListCmtDomainService {

    private final ListCmtDomainRepository listCmtDomainRepository;
    private final ModelMapper modelMapper;

    // 1차 수정 완료 - 창윤
    // 리스트 댓글 저장
    public void createListCmt(CreateListCmtRequest createListCmtRequest) {

        MyListComment newMyListMatzipCmt = modelMapper.map(createListCmtRequest, MyListComment.class);
        listCmtDomainRepository.save(newMyListMatzipCmt);
    }

    // 리스트 댓글 수정
    public Long updateListCmt(@Valid UpdateListCmtRequest updateListCmtRequest, long listCmtUserSeq) {

        Long listCmtSeq = updateListCmtRequest.getListCommentSeq();

        MyListComment existListCmt = listCmtDomainRepository.findById(listCmtSeq).orElseThrow();

        modelMapper.map(updateListCmtRequest, existListCmt);
        existListCmt.updateListCommentContent(existListCmt.getListCommentContent());

        listCmtDomainRepository.save(existListCmt);
        return listCmtSeq;

    }

    // 1차 수정 완료 - 창윤
    // 리스트 댓글 번호로 댓글 삭제
    public void deleteListCmt(Long listCommentSeq) {
        listCmtDomainRepository.deleteById(listCommentSeq);
    }

    // 1차 수정 완료 - 창윤
    // 리스트 댓글에서 댓글 작성자 고유번호 찾기
    public long getUserSeqFindById(Long listCommentSeq) {
        MyListComment myListComment = listCmtDomainRepository.findById(listCommentSeq)
                .orElseThrow(() -> new RestApiException(ErrorCode.NOT_FOUND));

        return myListComment.getListCommentUserSeq();
    }
}
