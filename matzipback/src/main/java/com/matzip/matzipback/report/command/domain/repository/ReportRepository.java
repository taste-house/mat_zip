package com.matzip.matzipback.report.command.domain.repository;

import com.matzip.matzipback.report.command.domain.aggregate.Report;

import java.util.Optional;

public interface ReportRepository {
    Report save(Report newReport);

    boolean existsByReporterUserSeqAndPostSeq(Long reporterUserSeq, Long postSeq);

    Optional<Report> findById(Long reportSeq);
}
