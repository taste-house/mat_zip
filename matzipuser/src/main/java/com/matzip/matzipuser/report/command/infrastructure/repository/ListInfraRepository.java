package com.matzip.matzipuser.report.command.infrastructure.repository;

import com.matzip.matzipuser.report.command.domain.aggregate.Report;
import com.matzip.matzipuser.report.command.domain.repository.ListDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListInfraRepository extends JpaRepository<Report, Long>, ListDomainRepository {
}
