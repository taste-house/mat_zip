package com.matzip.matzipuser.matzipList.command.infrastructure.repository;

import com.matzip.matzipuser.matzipList.command.domain.aggregate.MyListMatzip;
import com.matzip.matzipuser.matzipList.command.domain.repository.MatzipDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfraMatzipRepository extends JpaRepository<MyListMatzip, Long>, MatzipDomainRepository {

}
