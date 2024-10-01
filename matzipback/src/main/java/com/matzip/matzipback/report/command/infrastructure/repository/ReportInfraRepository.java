package com.matzip.matzipback.report.command.infrastructure.repository;

import com.matzip.matzipback.report.command.domain.aggregate.Report;
import com.matzip.matzipback.report.command.domain.repository.ReportDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportInfraRepository extends JpaRepository<Report, Long>, ReportDomainRepository {
}
