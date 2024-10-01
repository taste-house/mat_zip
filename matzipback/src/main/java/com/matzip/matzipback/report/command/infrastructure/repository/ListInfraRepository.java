package com.matzip.matzipback.report.command.infrastructure.repository;

import com.matzip.matzipback.matzipList.command.domain.aggregate.MyList;
import com.matzip.matzipback.report.command.domain.aggregate.Report;
import com.matzip.matzipback.report.command.domain.repository.ListDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListInfraRepository extends JpaRepository<Report, Long>, ListDomainRepository {
}
