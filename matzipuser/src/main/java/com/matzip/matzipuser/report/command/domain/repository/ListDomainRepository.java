package com.matzip.matzipuser.report.command.domain.repository;

import com.matzip.matzipuser.report.command.domain.aggregate.Report;

public interface ListDomainRepository {

    boolean existsByReporterUserSeqAndListSeq(Long reporterUserSeq, Long listSeq);

    Report save(Report newReport);

    boolean existsByReporterUserSeqAndListCommentSeq(Long reporterUserSeq, Long listCommentSeq);
}
