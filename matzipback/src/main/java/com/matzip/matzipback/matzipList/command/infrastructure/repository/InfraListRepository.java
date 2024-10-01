package com.matzip.matzipback.matzipList.command.infrastructure.repository;

import com.matzip.matzipback.matzipList.command.domain.aggregate.MyList;
import com.matzip.matzipback.matzipList.command.domain.repository.ListDomainRepository;
import org.modelmapper.internal.util.Lists;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfraListRepository extends JpaRepository<MyList, Long>, ListDomainRepository {
}
