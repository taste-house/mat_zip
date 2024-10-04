package com.matzip.matzipuser.matzipList.command.domain.repository;

import com.matzip.matzipuser.matzipList.command.application.dto.DeleteListCmtRequset;
import com.matzip.matzipuser.matzipList.command.domain.aggregate.MyListComment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public interface ListCmtDomainRepository {


    MyListComment save(MyListComment newMyListMatzipCmt);


    void deleteById(@NotBlank Long listCommentSeq);

    Optional<MyListComment> findById(Long listCmtSeq);


    MyListComment findByListCommentSeq(@NotNull Long listCommentSeq);
}
