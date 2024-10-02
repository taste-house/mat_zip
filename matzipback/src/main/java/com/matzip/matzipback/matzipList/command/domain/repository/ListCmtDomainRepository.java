package com.matzip.matzipback.matzipList.command.domain.repository;

import com.matzip.matzipback.matzipList.command.domain.aggregate.MyListComment;

import java.util.Optional;

public interface ListCmtDomainRepository {

    // 1차 수정 완료 - 창윤
    MyListComment save(MyListComment newMyListMatzipCmt);

    // 1차 수정 완료 - 창윤
    void deleteById(Long listCommentSeq);

    // 1차 수정 완료 - 창윤
    Optional<MyListComment> findById(Long listCmtSeq);
}
