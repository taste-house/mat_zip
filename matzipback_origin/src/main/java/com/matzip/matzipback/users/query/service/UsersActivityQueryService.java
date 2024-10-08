package com.matzip.matzipback.users.query.service;

import com.matzip.matzipback.users.query.dto.UsersActivityDTO;
import com.matzip.matzipback.users.query.mapper.UsersActivityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersActivityQueryService {

    private final UsersActivityMapper usersActivityMapper;

    public List<UsersActivityDTO> searchAllUsersActivity(long page, String active) {

        if (page <= 0) {
            page = 1;
        }

        long offset = (page - 1) * 10;
        int size = 10;

        long count = usersActivityMapper.countAllUsersActivity(active);

        if (offset > count) {
            page = count / 10;
            offset = (page - 1) * 10;
        }

        return usersActivityMapper.searchAllUsersActivity(offset, size, active);
    }
}
