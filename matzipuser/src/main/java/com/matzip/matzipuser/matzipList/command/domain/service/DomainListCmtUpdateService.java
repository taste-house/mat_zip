package com.matzip.matzipuser.matzipList.command.domain.service;

import com.matzip.matzipuser.matzipList.command.application.dto.UpdateListCmtRequest;
import com.matzip.matzipuser.matzipList.command.domain.aggregate.MyListComment;
import com.matzip.matzipuser.matzipList.command.domain.repository.ListCmtDomainRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DomainListCmtUpdateService {

    private final ListCmtDomainRepository listCmtDomainRepository;
    private final ModelMapper modelMapper;

    public Long updateListCmt(@Valid UpdateListCmtRequest updateListCmtRequest, long listCmtUserSeq) {

        Long listCmtSeq = updateListCmtRequest.getListCommentSeq();

        MyListComment existListCmt = listCmtDomainRepository.findById(listCmtSeq).orElseThrow();

        modelMapper.map(updateListCmtRequest, existListCmt);
        existListCmt.updateListCommentContent(existListCmt.getListCommentContent());

        listCmtDomainRepository.save(existListCmt);
        return listCmtSeq;

    }
}
