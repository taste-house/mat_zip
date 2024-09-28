package com.matzip.matzipback.matzipList.command.domain.repository;

import com.matzip.matzipback.matzipList.command.domain.aggregate.MyListMatzip;

public interface MatzipDomainRepository {


    MyListMatzip save(MyListMatzip newMatzip);
}
