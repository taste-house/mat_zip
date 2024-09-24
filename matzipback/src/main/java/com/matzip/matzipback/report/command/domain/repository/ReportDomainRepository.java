package com.matzip.matzipback.report.command.domain.repository;

import com.matzip.matzipback.report.command.domain.aggregate.Report;

public interface ReportDomainRepository {

    Report save(Report report);
}
