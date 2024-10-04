package com.matzip.matzipuser.report.command.application.service;

import com.matzip.matzipuser.matzipList.command.domain.aggregate.MyList;
import com.matzip.matzipuser.matzipList.command.domain.aggregate.MyListComment;
import com.matzip.matzipuser.matzipList.command.domain.service.DomainListCmtService;
import com.matzip.matzipuser.matzipList.command.domain.service.DomainListService;
import com.matzip.matzipuser.report.command.domain.aggregate.Report;
import com.matzip.matzipuser.report.command.domain.service.ListDomainService;
import com.matzip.matzipuser.report.command.domain.service.ReportDomainService;
import com.matzip.matzipuser.report.command.dto.ListCmtReportReqDTO;
import com.matzip.matzipuser.report.command.dto.ListReportReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportListService {


    private final DomainListService domainListService;
    private final DomainListCmtService domainListCmtService;
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

    public Report saveListCmtReport(ListCmtReportReqDTO listCmtReportReqDTO) {
        MyListComment foundListCmt = domainListCmtService.findByListCmtSeq(listCmtReportReqDTO.getListCommentSeq());

        Long reportedUserSeq = foundListCmt.getListCommentUserSeq();
        Long reporterUserSeq = /*CustomUserUtils.getCurrentUserSeq();*/ 2L;

        boolean isExistReport = listDomainService.checkListCmtExists(reporterUserSeq, listCmtReportReqDTO.getListCommentSeq());

        if (isExistReport) {
            return null;
        }

        Report newReport = Report.getListCmtReportSeq(reporterUserSeq, reportedUserSeq, listCmtReportReqDTO);

        return listDomainService.saveReport(newReport);
    }
}
