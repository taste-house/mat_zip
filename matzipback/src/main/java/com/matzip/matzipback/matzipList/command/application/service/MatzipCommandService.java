package com.matzip.matzipback.matzipList.command.application.service;

import com.matzip.matzipback.matzipList.command.application.dto.CreateMatzipRequest;
import com.matzip.matzipback.matzipList.command.application.dto.DeleteMatzipRequset;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyListMatzip;
import com.matzip.matzipback.matzipList.command.domain.repository.MatzipDomainRepository;
import com.matzip.matzipback.matzipList.command.domain.service.DomainListUpdateService;
import com.matzip.matzipback.matzipList.command.mapper.MatzipMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatzipCommandService {

    private final MatzipDomainRepository matzipDomainRepository;

    // 맛집 등록
    @Transactional
    public Long createMatzip(CreateMatzipRequest matzipRequest) {

        MyListMatzip newMatzip = MatzipMapper.toEntity(matzipRequest);

        MyListMatzip myMatzip = matzipDomainRepository.save(newMatzip);

        return myMatzip.getListMatzipSeq();
    }

    // 맛집 삭제
    @Transactional
    public void deleteMatzip(DeleteMatzipRequset deleteMatzipRequset) {

        matzipDomainRepository.deleteById(deleteMatzipRequset.getListMatzipSeq());
    }
}
