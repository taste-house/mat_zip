package com.matzip.matzipuser.matzipList.command.domain.repository;

import com.matzip.matzipuser.matzipList.command.domain.aggregate.MyList;

import com.matzip.matzipuser.users.command.domain.aggregate.Users;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.validation.constraints.NotNull;


import java.util.List;
import java.util.Optional;


public interface ListDomainRepository {


    MyList save(MyList myList);

    long countByListUserSeq(Long listUserSeq);

    Optional<MyList> findById(Long listSeq);


    void deleteById(Long listSeq);

    Optional<MyList> findByListSeq(Long listSeq);

    List<MyList> findByListUserSeq(Long listUserSeq);

    List saveAll(Iterable myLists);

    MyList findByListSeqAndListUserSeq(Long listSeq, Long listUserSeq);

}
