package com.matzip.matzipback.matzipList.query.service;

import com.matzip.matzipback.matzipList.query.dto.MatzipSearchDTO;
import com.matzip.matzipback.matzipList.query.mapper.MatzipQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatzipQueryService {

    private final MatzipQueryMapper matzipQueryMapper;

    public List<MatzipSearchDTO> searchMatzip(Long listSeq, String matzipTitle) {
        return matzipQueryMapper.getMatzip(listSeq, matzipTitle);
    }
}
