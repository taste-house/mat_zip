package com.matzip.matzipback.report.command.domain.repository;

import com.matzip.matzipback.report.command.domain.aggregate.Report;

public interface ListDomainRepository {

    boolean existsByReporterUserSeqAndListSeq(Long reporterUserSeq, Long listSeq);

    Report save(Report newReport);
}
