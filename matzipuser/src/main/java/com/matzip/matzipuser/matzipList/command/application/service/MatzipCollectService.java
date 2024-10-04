package com.matzip.matzipuser.matzipList.command.application.service;

import com.matzip.matzipuser.exception.ErrorCode;
import com.matzip.matzipuser.exception.RestApiException;
import com.matzip.matzipuser.matzipList.command.application.dto.MatzipCollectReq;
import com.matzip.matzipuser.matzipList.command.domain.aggregate.MyListMatzip;
import com.matzip.matzipuser.matzipList.command.domain.repository.MatzipCollectDomainRepository;
import com.matzip.matzipuser.matzipList.command.domain.repository.MatzipDomainRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatzipCollectService {

    private final MatzipCollectDomainRepository matzipCollectDomainRepository;
    private final MatzipDomainRepository matzipDomainRepository;
    private final ModelMapper modelMapper;
    @Transactional
    public Long collectMatzip(MatzipCollectReq matzipCollectReq) {

        MyListMatzip collectMatzip = modelMapper.map(matzipCollectReq, MyListMatzip.class);

        // 맛집 리스트에 해당 맛집이 존재하는지 검증
        boolean ExistListMatzipSeq = matzipDomainRepository
                .existsByRestaurantSeq(collectMatzip.getRestaurantSeq());
        if (!ExistListMatzipSeq) {
            throw new RestApiException(ErrorCode.BAD_REQUEST);
        }

        // 내 맛집 리스트에 이미 존재하는지 검증
        boolean ExistMyListMatzipSeq = matzipDomainRepository
                .existsByListSeqAndRestaurantSeq(collectMatzip.getListSeq(), collectMatzip.getRestaurantSeq());
        if(ExistMyListMatzipSeq) {
            throw new RestApiException(ErrorCode.CONFLICT);
        }

        MyListMatzip myMatzip = matzipCollectDomainRepository.save(collectMatzip);

        return myMatzip.getListMatzipSeq();
    }
}
