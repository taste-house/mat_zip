package com.matzip.matzipback.matzipList.command.application.service;

import com.matzip.matzipback.matzipList.command.domain.aggregate.MyList;
import com.matzip.matzipback.matzipList.command.application.dto.CreateListRequest;
import com.matzip.matzipback.matzipList.command.domain.repository.ListDomainRepository;
import com.matzip.matzipback.matzipList.command.mapper.ListMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListCommandService {

        private final ListDomainRepository listDomainRepository;


    @Transactional
    public Long createList(CreateListRequest listRequest) {

        // 로그인한 사람의 유저 시퀀스를 가져오는 기능(권한이 들어있는 유저 시퀀스)
//        Long listUserSeq = CustomUserUtils.getCurrentUserSeq();

        // 테스트용 코드 생성 (권한이 없는 유저 시퀀스)
        long listUserSeq = 4L;
        // 특정 유저의 리스트 서랍에 있는 리스트 카운트
        long countListLevel = listDomainRepository.countByListUserSeq(listUserSeq);

        // 카운트 된 리스트 개수에 + 1을 더해서 1부터 입력되게 설정
        int listLevel = (int) countListLevel + 1;

        MyList newList = ListMapper.toEntity(listRequest, listUserSeq, listLevel);

        MyList myList = listDomainRepository.save(newList);

        return myList.getListSeq();
    }

    @Transactional
    public void deleteList(Long listSeq) {

        listDomainRepository.deleteById(listSeq);
    }
}
