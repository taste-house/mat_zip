package com.matzip.matzipback.matzipList.command.domain.repository;

import com.matzip.matzipback.matzipList.command.domain.aggregate.MyList;
import com.matzip.matzipback.users.command.domain.aggregate.Users;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ListDomainRepository {


    MyList save(MyList myList);

}