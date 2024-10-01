package com.matzip.matzipback.matzipList.command.domain.repository;

import com.matzip.matzipback.matzipList.command.application.dto.DeleteListCmtRequset;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyListComment;
import jakarta.validation.constraints.NotBlank;

public interface ListCmtDomainRepository {


    MyListComment save(MyListComment newMyListMatzipCmt);


    void deleteById(@NotBlank Long listCommentSeq);
}
