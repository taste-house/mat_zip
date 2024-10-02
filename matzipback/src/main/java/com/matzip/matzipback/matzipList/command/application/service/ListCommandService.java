package com.matzip.matzipback.matzipList.command.application.service;

import com.matzip.matzipback.exception.ErrorCode;
import com.matzip.matzipback.exception.RestApiException;
import com.matzip.matzipback.matzipList.command.application.dto.DeleteListRequest;
import com.matzip.matzipback.matzipList.command.application.dto.UpdateListRequest;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyList;
import com.matzip.matzipback.matzipList.command.application.dto.CreateListRequest;
import com.matzip.matzipback.matzipList.command.domain.repository.ListDomainRepository;
import com.matzip.matzipback.matzipList.command.domain.service.DomainListUpdateService;
import com.matzip.matzipback.matzipList.command.mapper.ListMapper;
import com.matzip.matzipback.users.command.domain.service.UserActivityDomainService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListCommandService {

        private final ListDomainRepository listDomainRepository;
        private final DomainListUpdateService domainListUpdateService;
        private final UserActivityDomainService userActivityDomainService;

    // 리스트 등록
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

        // 리스트 등록 시 점수 획득(3점)
        userActivityDomainService.updateUserActivityPoint(listUserSeq, 3);

        return myList.getListSeq();
    }

    // 리스트 삭제
    @Transactional
    public void deleteList(DeleteListRequest deleteListRequest) {
        listDomainRepository.deleteById(deleteListRequest.getListSeq());

        // 리스트 삭제 시 점수 삭제(-3점)
        MyList listUser = listDomainRepository
                .findByListSeq(deleteListRequest.getListSeq()).orElseThrow(() -> new RestApiException(ErrorCode.NOT_FOUND));
        userActivityDomainService.updateUserActivityPoint(listUser.getListUserSeq(), -3);
    }


    // 리스트 수정
    @Transactional
    public Long updateList(UpdateListRequest updateListRequest) {

        //로그인한 사람의 유저 시퀀스를 가져오는 기능(권한이 들어있는 유저 시퀀스)
//        Long listUserSeq = CustomUserUtils.getCurrentUserSeq();

        // 테스트용 코드 생성
        long listUserSeq = 4L;

        return domainListUpdateService.updateList(updateListRequest, listUserSeq);

    }

}
