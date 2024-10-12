package com.matzip.matzipback.matzipList.query.service;

import com.matzip.matzipback.matzipList.query.dto.ListCommentDTO;
import com.matzip.matzipback.matzipList.query.mapper.ListCommentQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListCommentQueryService {

    private final ListCommentQueryMapper listCommentQueryMapper;


    public List<ListCommentDTO> getListComments(Long listSeq) {
        return listCommentQueryMapper.getListComments(listSeq);
    }
}
