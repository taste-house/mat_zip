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

        Long listSeq = updateMatzipRequest.getListSeq();
        Long listMatzipSeq = updateMatzipRequest.getListMatzipSeq();

        MyListMatzip existList = matzipDomainRepository.findById(listSeq)
                .orElseThrow(() -> new IllegalArgumentException("해당 리스트가 존재하지 않습니다. : " + listSeq));
        MyListMatzip existMatzip = matzipDomainRepository.findById(listMatzipSeq)
                .orElseThrow(() -> new IllegalArgumentException("해당 맛집은 존재하지 않습니다. : " + listMatzipSeq));

        modelMapper.map(updateMatzipRequest, existMatzip);
        existMatzip.setListSeq(listMatzipSeq);
        existMatzip.setListMatzipSeq(listMatzipSeq);
        existMatzip.setListMatzipComment(updateMatzipRequest.getListMatzipComment());

        return existMatzip.getListMatzipSeq();


    }
}
