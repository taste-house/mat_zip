package com.matzip.matzipuser.matzipList.command.domain.service;

import com.matzip.matzipuser.matzipList.command.application.dto.UpdateListRequest;
import com.matzip.matzipuser.matzipList.command.domain.aggregate.MyList;
import com.matzip.matzipuser.matzipList.command.domain.repository.ListDomainRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DomainListUpdateService {

    private final ModelMapper modelMapper;
    private final ListDomainRepository listDomainRepository;


    public long updateList(UpdateListRequest updateListRequest, long listUserSeq) {

        Long listSeq = updateListRequest.getListSeq();

        MyList existList = listDomainRepository.findById(listSeq)
                .orElseThrow();

        modelMapper.map(updateListRequest, existList);
        existList.updateListTitle(updateListRequest.getListTitle());
        existList.updateListContent(updateListRequest.getListContent());


        listDomainRepository.save(existList);

        return existList.getListSeq();

    }
}
