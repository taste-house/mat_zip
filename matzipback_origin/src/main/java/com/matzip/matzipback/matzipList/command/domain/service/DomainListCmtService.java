package com.matzip.matzipback.matzipList.command.domain.service;

import com.matzip.matzipback.matzipList.command.domain.aggregate.MyListComment;
import com.matzip.matzipback.matzipList.command.domain.repository.ListCmtDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DomainListCmtService {

    private final ListCmtDomainRepository listCmtDomainRepository;


    public MyListComment findByListCmtSeq(Long listCommentSeq) {
        return listCmtDomainRepository.findById(listCommentSeq).orElse(null);
    }
}
