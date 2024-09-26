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

        // 로그인한 사람의 유저 시퀀스를 가져오는 기능(권한이 들어있는 유저 시퀀스)
//        Long listUserSeq = CustomUserUtils.getCurrentUserSeq();

        // 테스트용 코드 생성 (권한이 없는 유저 시퀀스)
        long listUserSeq = 3L;

        int listLevel = Math.toIntExact(listQueryMapper.getCountList(listUserSeq));

        MyList newList = ListMapper.toEntity(listRequest, listUserSeq, listLevel);

        MyList myList = listDomainRepository.save(newList);

        return myList.getListSeq();
    }
}
