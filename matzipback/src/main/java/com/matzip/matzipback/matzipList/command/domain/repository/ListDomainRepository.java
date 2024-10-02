package com.matzip.matzipback.matzipList.command.domain.repository;

import com.matzip.matzipback.matzipList.command.domain.aggregate.MyList;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;


public interface ListDomainRepository {


    MyList save(MyList myList);

    long countByListUserSeq(Long listUserSeq);

    void deleteById(Long listSeq);

    Optional<MyList> findById(Long listSeq);

    List<MyList> findByListUserSeq(Long listUserSeq);

    List saveAll(Iterable myLists);

    MyList findByListSeqAndListUserSeq(Long listSeq, Long listUserSeq);
}
