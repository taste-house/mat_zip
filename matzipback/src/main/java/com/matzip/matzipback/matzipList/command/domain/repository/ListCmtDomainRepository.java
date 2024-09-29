package com.matzip.matzipback.matzipList.command.domain.repository;

import com.matzip.matzipback.matzipList.command.domain.aggregate.MyListComment;

public interface ListCmtDomainRepository {


    MyListComment save(MyListComment newMyListMatzipCmt);
}
