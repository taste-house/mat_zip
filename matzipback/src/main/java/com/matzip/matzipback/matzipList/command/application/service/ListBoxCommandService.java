package com.matzip.matzipback.matzipList.command.application.service;

import com.matzip.matzipback.common.util.CustomUserUtils;
import com.matzip.matzipback.exception.ErrorCode;
import com.matzip.matzipback.exception.RestApiException;
import com.matzip.matzipback.matzipList.command.application.dto.ListBoxUpdateRequest;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyList;
import com.matzip.matzipback.matzipList.command.domain.repository.ListDomainRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListBoxCommandService {

    private final ListDomainRepository listDomainRepository;

    @Transactional
    public MyList updateListBoxLevel(@Valid ListBoxUpdateRequest listBoxUpdateRequest) {
        // 입력받은 레벨 변수 선언
        int inputListBoxLevel = listBoxUpdateRequest.getListLevel();
        Long inputListSeq = listBoxUpdateRequest.getListSeq();


        Long ListUserSeq = CustomUserUtils.getCurrentUserSeq();

        // 전체 리스트 불러오기
        List<MyList> allLists = listDomainRepository.findByListUserSeq(ListUserSeq);


        // 변경된 레벨 적용
        MyList targetList = listDomainRepository.findByListSeqAndListUserSeq(listBoxUpdateRequest.getListSeq(), ListUserSeq);
        int originListLevel = targetList.getListLevel();
        if (originListLevel != listBoxUpdateRequest.getListLevel()) {
            targetList.updateListLevel(inputListBoxLevel);

            for (MyList nowList : allLists) {
                if (nowList.getListLevel() >= inputListBoxLevel && nowList.getListLevel() < originListLevel  && nowList.getListSeq() != inputListSeq) {
                    nowList.updateListLevel(nowList.getListLevel() + 1);
                } else if (nowList.getListLevel() > originListLevel && nowList.getListSeq() != inputListSeq) {
                    nowList.updateListLevel(nowList.getListLevel() - 1);
                }
            }

            // 저장
            listDomainRepository.saveAll(allLists);

            return targetList;
        }else{
            throw new RestApiException(ErrorCode.BAD_REQUEST);
        }

    }
}
