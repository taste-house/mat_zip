package com.matzip.matzipback.report.command.application.service;

import com.matzip.matzipback.board.command.domain.aggregate.Post;
import com.matzip.matzipback.board.command.domain.service.PostDomainService;
import com.matzip.matzipback.report.command.domain.aggregate.Report;
import com.matzip.matzipback.report.command.domain.repository.ReportDomainRepository;
import com.matzip.matzipback.report.command.domain.service.ReportDomainService;
import com.matzip.matzipback.report.command.dto.PostReportReqDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportCommandService {

    private final PostDomainService postDomainService;
    private final ReportDomainService reportDomainService;

    // 게시글 신고 기능
    public Report savePostReport(PostReportReqDTO postReportReqDTO) {

        Post foundPost = postDomainService.findByPostSeq(postReportReqDTO.getPostSeq());
        Long reportedUserSeq = foundPost.getPostUserSeq();
        Long reporterUserSeq = /*CustomUserUtils.getCurrentUserSeq();*/ 2L;

        boolean isExistReport = reportDomainService.checkReportExists(reporterUserSeq, postReportReqDTO.getPostSeq());

        if (isExistReport) {
            return null;
        }

        Report newReport = Report.getReportSeq(reporterUserSeq, reportedUserSeq, postReportReqDTO);

        return reportDomainService.saveReport(newReport);
    }
}
