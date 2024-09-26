package com.matzip.matzipback.report.command.domain.service;

import com.matzip.matzipback.report.command.domain.aggregate.Report;
import com.matzip.matzipback.report.command.domain.repository.ReportDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportDomainService {

    private final ReportDomainRepository reportDomainRepository;

    // 같은 유저가 똑같은 신고를 하지 못하도록 같은 신고가 있는지를 체크
    public boolean checkReportExists(Long reporterUserSeq, Long postSeq) {
        return reportDomainRepository.existsByReporterUserSeqAndPostSeq(reporterUserSeq, postSeq);
    }

    public Report saveReport(Report report) {
        return reportDomainRepository.save(report);
    }
}
