package com.matzip.matzipback.matzipList.command.application.service;

import com.matzip.matzipback.matzipList.command.application.dto.CreateListCmtRequest;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyListComment;
import com.matzip.matzipback.matzipList.command.domain.repository.ListCmtDomainRepository;
import com.matzip.matzipback.matzipList.command.mapper.ListCmtMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListCmtCommandService {

    private final ListCmtDomainRepository listCmtDomainRepository;

    // 리스트 댓글 등록
    @Transactional
    public Long createListCmt(CreateListCmtRequest listCmtRequest) {

        MyListComment newMyListMatzipCmt = ListCmtMapper.toEntity(listCmtRequest);

        MyListComment MyListMatzipCmt = listCmtDomainRepository.save(newMyListMatzipCmt);

        return MyListMatzipCmt.getListCommentSeq();

    }
}
