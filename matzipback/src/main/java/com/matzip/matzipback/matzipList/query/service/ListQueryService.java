package com.matzip.matzipback.matzipList.query.service;

import com.matzip.matzipback.matzipList.query.dto.ListContentDTO;
import com.matzip.matzipback.matzipList.query.dto.ListSearchAllDTO;
import com.matzip.matzipback.matzipList.query.dto.ListSearchUserDTO;
import com.matzip.matzipback.matzipList.query.mapper.ListQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListQueryService {

    private final ListQueryMapper listQueryMapper;

    public List<ListSearchAllDTO> getListBox(Long listUserSeq) {
        return listQueryMapper.getListBox(listUserSeq);
    }

    public List<ListSearchUserDTO> getUserListBox(Long listUserSeq) {
        return listQueryMapper.getUserListBox(listUserSeq);
    }

    public List<ListContentDTO> getListContests(Long listSeq) {
        return listQueryMapper.getListContests(listSeq);
    }
}
