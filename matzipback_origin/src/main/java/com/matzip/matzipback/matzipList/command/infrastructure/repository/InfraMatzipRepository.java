package com.matzip.matzipback.matzipList.command.infrastructure.repository;

import com.matzip.matzipback.matzipList.command.domain.aggregate.MyListMatzip;
import com.matzip.matzipback.matzipList.command.domain.repository.MatzipDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfraMatzipRepository extends JpaRepository<MyListMatzip, Long>, MatzipDomainRepository {

}
