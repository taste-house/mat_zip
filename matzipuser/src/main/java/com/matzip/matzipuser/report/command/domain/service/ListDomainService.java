package com.matzip.matzipuser.report.command.domain.service;


import com.matzip.matzipuser.report.command.domain.aggregate.Report;
import com.matzip.matzipuser.report.command.domain.repository.ListDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListDomainService {

    private final ListDomainRepository listDomainRepository;

    public boolean checkListExists(Long reporterUserSeq, Long listSeq) {
        return listDomainRepository.existsByReporterUserSeqAndListSeq(reporterUserSeq, listSeq);
    }

    public Report saveReport(Report newReport) {
        return listDomainRepository.save(newReport);
    }

    public boolean checkListCmtExists(Long reporterUserSeq, Long listCommentSeq) {
        return listDomainRepository.existsByReporterUserSeqAndListCommentSeq(reporterUserSeq, listCommentSeq);
    }
}
