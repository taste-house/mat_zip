package com.matzip.matzipback.matzipList.command.application.service;

import com.matzip.matzipback.common.util.CustomUserUtils;
import com.matzip.matzipback.matzipList.command.application.dto.CreateListCmtRequest;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyListComment;
import com.matzip.matzipback.matzipList.command.domain.repository.ListCmtDomainRepository;
import com.matzip.matzipback.matzipList.command.mapper.ListCmtMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListCmtCommandService {

    private final ListCmtDomainRepository listCmtDomainRepository;

    // 리스트 댓글 등록
    @Transactional
    public Long createListCmt(CreateListCmtRequest listCmtRequest) {
        // 로그인한 사람의 유저 시퀀스를 가져오는 기능(권한이 들어있는 유저 시퀀스)
//        Long listCommentUserSeq = CustomUserUtils.getCurrentUserSeq();

        long listCommentUserSeq = 1L;

        MyListComment newMyListMatzipCmt = ListCmtMapper.toEntity(listCmtRequest, listCommentUserSeq);

        MyListComment MyListMatzipCmt = listCmtDomainRepository.save(newMyListMatzipCmt);

        return MyListMatzipCmt.getListCommentSeq();

    }
}
