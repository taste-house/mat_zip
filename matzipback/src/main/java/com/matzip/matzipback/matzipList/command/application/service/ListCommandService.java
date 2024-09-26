package com.matzip.matzipback.matzipList.command.application.service;

import com.matzip.matzipback.common.util.CustomUserUtils;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyList;
import com.matzip.matzipback.matzipList.command.application.dto.CreateListRequest;
import com.matzip.matzipback.matzipList.command.domain.repository.ListDomainRepository;
import com.matzip.matzipback.matzipList.command.mapper.ListMapper;
import com.matzip.matzipback.matzipList.query.mapper.ListQueryMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListCommandService {

        private final ListDomainRepository listDomainRepository;
        private final ListQueryMapper listQueryMapper;

    @Transactional
    public Long createList(CreateListRequest listRequest) {

        Long listUserSeq = CustomUserUtils.getCurrentUserSeq();

        int listLevel = listQueryMapper.getCountList(listUserSeq);

        MyList newList = ListMapper.toEntity(listRequest, listUserSeq, listLevel);

        MyList myList = listDomainRepository.save(newList);

        return myList.getListSeq();
    }
}
