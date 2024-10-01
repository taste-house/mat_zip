package com.matzip.matzipback.report.command.application.service;

import com.matzip.matzipback.board.command.domain.service.PostDomainService;
import com.matzip.matzipback.matzipList.command.domain.aggregate.MyList;
import com.matzip.matzipback.matzipList.command.domain.service.DomainListService;
import com.matzip.matzipback.report.command.domain.aggregate.Report;
import com.matzip.matzipback.report.command.domain.repository.ListDomainRepository;
import com.matzip.matzipback.report.command.domain.service.ListDomainService;
import com.matzip.matzipback.report.command.domain.service.ReportDomainService;
import com.matzip.matzipback.report.command.dto.ListReportReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportListService {


    private final DomainListService domainListService;
    private final ListDomainService listDomainService;
    private final ReportDomainService reportDomainService;

    public Report saveListReport(ListReportReqDTO listReportReqDTO) {

        MyList foundList = domainListService.findByListSeq(listReportReqDTO.getListSeq());

        Long reportedUserSeq = foundList.getListUserSeq();
        Long reporterUserSeq = /*CustomUserUtils.getCurrentUserSeq();*/ 2L;

        boolean isExistReport = listDomainService.checkListExists(reporterUserSeq, listReportReqDTO.getListSeq());

        if (isExistReport) {
            return null;
        }

        Report newReport = Report.getListReportSeq(reporterUserSeq, reportedUserSeq, listReportReqDTO);

        return listDomainService.saveReport(newReport);

    }
}
