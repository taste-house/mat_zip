package com.matzip.matzipuser.report.command.domain.repository;

import com.matzip.matzipuser.report.command.domain.aggregate.Report;

import java.util.Optional;

public interface ReportDomainRepository {
    Report save(Report newReport);

    boolean existsByReporterUserSeqAndPostSeq(Long reporterUserSeq, Long postSeq);

    Optional<Report> findById(Long reportSeq);
}
