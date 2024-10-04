package com.matzip.matzipuser.matzipList.command.infrastructure.repository;

import com.matzip.matzipuser.matzipList.command.domain.aggregate.MyListComment;
import com.matzip.matzipuser.matzipList.command.domain.repository.ListCmtDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfraListCmtDomainRespository extends JpaRepository<MyListComment, Long>, ListCmtDomainRepository {
}
