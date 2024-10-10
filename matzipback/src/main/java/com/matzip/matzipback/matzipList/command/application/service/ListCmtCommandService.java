package com.matzip.matzipback.matzipList.command.application.service;

import com.matzip.matzipback.common.util.CustomUserUtils;
import com.matzip.matzipback.common.util.UserActivityFeignClient;
import com.matzip.matzipback.common.util.dto.UpdateUserActivityPointDTO;
import com.matzip.matzipback.matzipList.command.application.dto.CreateListCmtRequest;
import com.matzip.matzipback.matzipList.command.application.dto.DeleteListCmtRequset;
import com.matzip.matzipback.matzipList.command.application.dto.UpdateListCmtRequest;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyListComment;
import com.matzip.matzipback.matzipList.command.domain.repository.ListCmtDomainRepository;
import com.matzip.matzipback.matzipList.command.domain.service.DomainListCmtUpdateService;
import com.matzip.matzipback.matzipList.command.mapper.ListCmtMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListCmtCommandService {

    private final ListCmtDomainRepository listCmtDomainRepository;
    private final DomainListCmtUpdateService domainListCmtUpdateService;
    private final UserActivityFeignClient userActivityFeignClient;

    // 리스트 댓글 등록
    @Transactional
    public Long createListCmt(CreateListCmtRequest listCmtRequest) {
        // 로그인한 사람의 유저 시퀀스를 가져오는 기능(권한이 들어있는 유저 시퀀스)
        Long listCommentUserSeq = CustomUserUtils.getCurrentUserSeq();

        MyListComment newMyListMatzipCmt = ListCmtMapper.toEntity(listCmtRequest, listCommentUserSeq);

        MyListComment MyListMatzipCmt = listCmtDomainRepository.save(newMyListMatzipCmt);

        // 리스트 등록 시 점수 획득(1점)
        userActivityFeignClient.updateUserActivityPoint(
                new UpdateUserActivityPointDTO(listCommentUserSeq, 1));

        return MyListMatzipCmt.getListCommentSeq();

    }

    // 리스트 댓글 삭제
    @Transactional
    public void deleteListCmt(DeleteListCmtRequset deleteListCmtRequset) {
        listCmtDomainRepository.deleteById(deleteListCmtRequset.getListCommentSeq());

        // 리스트 삭제 시 획득 점수 차감(-1점)
        MyListComment commentUser = listCmtDomainRepository.findByListCommentSeq(deleteListCmtRequset.getListCommentSeq());
        userActivityFeignClient.updateUserActivityPoint(
                new UpdateUserActivityPointDTO(
                        commentUser.getListCommentUserSeq(), -1));
    }

    // 리스트 댓글 수정
    public Long updateListCmt(@Valid UpdateListCmtRequest updateListCmtRequest) {
        //로그인한 사람의 유저 시퀀스를 가져오는 기능(권한이 들어있는 유저 시퀀스)
        Long listCmtUserSeq = CustomUserUtils.getCurrentUserSeq();



        return domainListCmtUpdateService.updateListCmt(updateListCmtRequest, listCmtUserSeq);
    }
}
