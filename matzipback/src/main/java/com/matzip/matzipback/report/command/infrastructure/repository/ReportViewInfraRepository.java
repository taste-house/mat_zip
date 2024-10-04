package com.matzip.matzipback.report.command.infrastructure.repository;

import com.matzip.matzipback.report.command.domain.aggregate.ReportView;
import com.matzip.matzipback.report.command.domain.repository.ReportViewRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportViewInfraRepository extends ReportViewRepository, JpaRepository<ReportView, Long> {
}
