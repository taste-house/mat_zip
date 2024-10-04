package com.matzip.matzipuser.report.command.infrastructure.repository;

import com.matzip.matzipuser.report.command.domain.aggregate.Report;
import com.matzip.matzipuser.report.command.domain.repository.ReportDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportInfraRepository extends JpaRepository<Report, Long>, ReportDomainRepository {
}
