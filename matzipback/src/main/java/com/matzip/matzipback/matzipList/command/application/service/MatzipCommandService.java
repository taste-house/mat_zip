package com.matzip.matzipback.matzipList.command.application.service;

import com.matzip.matzipback.matzipList.command.application.dto.CreateMatzipRequest;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyListMatzip;
import com.matzip.matzipback.matzipList.command.domain.repository.MatzipDomainRepository;
import com.matzip.matzipback.matzipList.command.mapper.MatzipMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatzipCommandService {

    private final MatzipDomainRepository matzipDomainRepository;

    @Transactional
    public Long createMatzip(CreateMatzipRequest matzipRequest) {

        MyListMatzip newMatzip = MatzipMapper.toEntity(matzipRequest);

        MyListMatzip myMatzip = matzipDomainRepository.save(newMatzip);

        return myMatzip.getListMatzipSeq();
    }
}
