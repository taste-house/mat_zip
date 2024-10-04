package com.matzip.matzipuser.matzipList.command.infrastructure.repository;

import com.matzip.matzipuser.matzipList.command.domain.aggregate.MyListMatzip;
import com.matzip.matzipuser.matzipList.command.domain.repository.MatzipCollectDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfraMatzipCollectRepository extends JpaRepository<MyListMatzip, Long>, MatzipCollectDomainRepository {
}
