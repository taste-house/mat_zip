package com.matzip.matzipback.matzipList.command.infrastructure.repository;

import com.matzip.matzipback.matzipList.command.domain.aggregate.MyListComment;
import com.matzip.matzipback.matzipList.command.domain.repository.ListCmtDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfraListCmtDomainRespository extends JpaRepository<MyListComment, Long>, ListCmtDomainRepository {
}
