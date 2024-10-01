package com.matzip.matzipback.matzipList.command.domain.service;

import com.matzip.matzipback.matzipList.command.application.dto.UpdateMatzipRequest;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyList;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyListMatzip;
import com.matzip.matzipback.matzipList.command.domain.repository.ListDomainRepository;
import com.matzip.matzipback.matzipList.command.domain.repository.MatzipDomainRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DomainMatzipService {

    private final ModelMapper modelMapper;
    private final MatzipDomainRepository matzipDomainRepository;
    private final ListDomainRepository listDomainRepository;

    public Long updateMatzip(UpdateMatzipRequest updateMatzipRequest) {

        Long listMatzipSeq = updateMatzipRequest.getListMatzipSeq();

        MyListMatzip existMatzip = matzipDomainRepository.findById(listMatzipSeq).orElseThrow();

        modelMapper.map(updateMatzipRequest, existMatzip);
        existMatzip.updateListSeq(updateMatzipRequest.getListSeq());
        existMatzip.updateListMatzipSeq(updateMatzipRequest.getListMatzipSeq());
        existMatzip.updateRestaurantSeq(updateMatzipRequest.getRestaurantSeq());
        existMatzip.updateListMatzipComment(updateMatzipRequest.getListMatzipComment());

        return existMatzip.getListMatzipSeq();


    }
}
