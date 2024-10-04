package com.matzip.matzipuser.matzipList.command.application.service;

import com.matzip.matzipuser.matzipList.command.application.dto.CreateMatzipRequest;
import com.matzip.matzipuser.matzipList.command.application.dto.DeleteMatzipRequest;
import com.matzip.matzipuser.matzipList.command.application.dto.UpdateMatzipRequest;
import com.matzip.matzipuser.matzipList.command.domain.aggregate.MyListMatzip;
import com.matzip.matzipuser.matzipList.command.domain.repository.MatzipDomainRepository;
import com.matzip.matzipuser.matzipList.command.domain.service.DomainMatzipService;
import com.matzip.matzipuser.matzipList.command.mapper.MatzipMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatzipCommandService {

    private final MatzipDomainRepository matzipDomainRepository;
    private final DomainMatzipService domainMatzipService;


    // 맛집 등록
    @Transactional
    public Long createMatzip(CreateMatzipRequest matzipRequest) {

        MyListMatzip newMatzip = MatzipMapper.toEntity(matzipRequest);

        MyListMatzip myMatzip = matzipDomainRepository.save(newMatzip);

        return myMatzip.getListMatzipSeq();
    }

    // 맛집 삭제
    @Transactional
    public void deleteMatzip(DeleteMatzipRequest deleteMatzipRequest) {

        matzipDomainRepository.deleteById(deleteMatzipRequest.getListMatzipSeq());
    }

    // 맛집 수정
    @Transactional
    public Long updateMatzip(UpdateMatzipRequest updateMatzipRequest) {
        return domainMatzipService.updateMatzip(updateMatzipRequest);
    }
}
