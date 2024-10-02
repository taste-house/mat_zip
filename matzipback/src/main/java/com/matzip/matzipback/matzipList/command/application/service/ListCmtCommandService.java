package com.matzip.matzipback.matzipList.command.application.service;

import com.matzip.matzipback.matzipList.command.application.dto.CreateListCmtRequest;
import com.matzip.matzipback.matzipList.command.application.dto.DeleteListCmtRequset;
import com.matzip.matzipback.matzipList.command.application.dto.UpdateListCmtRequest;
import com.matzip.matzipback.matzipList.command.domain.repository.ListCmtDomainRepository;
import com.matzip.matzipback.matzipList.command.domain.service.DomainListCmtUpdateService;
import com.matzip.matzipback.matzipList.command.domain.service.ListCmtDomainService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListCmtCommandService {

    private final ListCmtDomainRepository listCmtDomainRepository;
    private final DomainListCmtUpdateService domainListCmtUpdateService;
    private final ListCmtDomainService listCmtDomainService;

    // 1차 수정 완료 - 창윤
    // 리스트 댓글 등록
    @Transactional
    public void createListCmt(CreateListCmtRequest createListCmtRequest) {
        // 로그인한 사람의 유저 시퀀스를 가져오는 기능(권한이 들어있는 유저 시퀀스)
//        Long listCommentUserSeq = CustomUserUtils.getCurrentUserSeq();

        long loginUserSeq = 4L;

        createListCmtRequest.setListCommentUserSeq(loginUserSeq);

        listCmtDomainService.createListCmt(createListCmtRequest);
    }

    // 리스트 댓글 삭제
    @Transactional
    public void deleteListCmt(DeleteListCmtRequset deleteListCmtRequset) {
        listCmtDomainRepository.deleteById(deleteListCmtRequset.getListCommentSeq());
    }

    // 리스트 댓글 수정
    public Long updateListCmt(@Valid UpdateListCmtRequest updateListCmtRequest) {
        //로그인한 사람의 유저 시퀀스를 가져오는 기능(권한이 들어있는 유저 시퀀스)
//        Long listUserSeq = CustomUserUtils.getCurrentUserSeq();

        // 테스트용 코드 생성
        long listCmtUserSeq = 4L;

        return listCmtDomainService.updateListCmt(updateListCmtRequest, listCmtUserSeq);
    }
}
