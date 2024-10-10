package com.matzip.matzipback.matzipList.command.domain.service;

import com.matzip.matzipback.matzipList.command.domain.aggregate.MyList;
import com.matzip.matzipback.matzipList.command.domain.repository.ListDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DomainListService {

    private final ListDomainRepository listDomainRepository;

    public MyList findByListSeq(Long listSeq) {
        return listDomainRepository.findById(listSeq).orElse(null);
    }
}
