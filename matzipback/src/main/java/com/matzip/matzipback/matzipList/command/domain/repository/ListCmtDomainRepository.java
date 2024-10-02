package com.matzip.matzipback.matzipList.command.domain.repository;

import com.matzip.matzipback.matzipList.command.domain.aggregate.MyListComment;

import java.util.Optional;

public interface ListCmtDomainRepository {

    // 1차 수정 완료 - 창윤
    MyListComment save(MyListComment newMyListMatzipCmt);


    void deleteById(Long listCommentSeq);

    Optional<MyListComment> findById(Long listCmtSeq);
}
