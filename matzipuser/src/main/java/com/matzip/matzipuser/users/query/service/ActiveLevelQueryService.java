package com.matzip.matzipuser.users.query.service;

import com.matzip.matzipuser.users.query.dto.ActiveLevelDTO;
import com.matzip.matzipuser.users.query.mapper.ActiveLevelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActiveLevelQueryService {

    private final ActiveLevelMapper activeLevelMapper;

    @Transactional
    public List<ActiveLevelDTO> searchAllActiveLevel(long page) {

        if (page <= 0) {
            page = 1;
        }

        int size = 10;
        long offset = (page - 1) * size;


        long count = activeLevelMapper.countAllActiveLevel();

        if (offset > count) {
            page = count / size;
            offset = (page - 1) * size;
        }


        return activeLevelMapper.findAllActiveLevel(offset, size);
    }
}
