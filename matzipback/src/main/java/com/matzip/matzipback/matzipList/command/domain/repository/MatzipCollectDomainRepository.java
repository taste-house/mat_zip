package com.matzip.matzipback.matzipList.command.domain.repository;

import com.matzip.matzipback.matzipList.command.domain.aggregate.MyListMatzip;

import java.util.Optional;

public interface MatzipCollectDomainRepository {

    //Optional 객체 값 가져올때 비어있으면 NoSuchElementException 발생
    Optional<MyListMatzip> findById(Long listSeq);

    MyListMatzip save(MyListMatzip existMatzip);
}
