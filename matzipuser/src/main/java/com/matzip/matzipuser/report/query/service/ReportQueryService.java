package com.matzip.matzipuser.report.query.service;

import com.matzip.matzipuser.exception.RestApiException;
import com.matzip.matzipuser.report.query.dto.ReportDTO;
import com.matzip.matzipuser.report.query.dto.ReportListResponse;
import com.matzip.matzipuser.report.query.dto.ReportDetailResponse;
import com.matzip.matzipuser.report.query.mapper.ReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.matzip.matzipuser.exception.ErrorCode.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ReportQueryService {

    private final ReportMapper reportMapper;

    // 신고 검색 및 조회
    @Transactional(readOnly = true)
    public ReportListResponse getReports(Integer page, Integer size, Long reporterUserSeq, Long reportedUserSeq, String reportStatus, Long category, Long sequence) {
        int offset = (page - 1) * size;

        List<ReportDTO> reports = reportMapper.selectReports(offset, size, reporterUserSeq, reportedUserSeq, reportStatus, category, sequence);

        for (ReportDTO report : reports) {
            report.setReasons(reportMapper.selectReportReasons(report.getReportSeq()));
        }

        long totalItems = reportMapper.countReports(reporterUserSeq, reportedUserSeq, reportStatus, category, sequence);

        return ReportListResponse.builder().reports(reports)
                .currentPage(page)
                .totalPages((int) Math.ceil((double) totalItems / size))
                .totalItems(totalItems)
                .build();
    }

    // 신고 상세 조회
    public ReportDetailResponse getReport(Long reportSeq) {
        ReportDTO report = reportMapper.selectReportBySeq(reportSeq);

        if (report == null) {
            throw new RestApiException(NOT_FOUND);
        } else {
            report.setReasons(reportMapper.selectReportReasons(report.getReportSeq()));
        }

        return new ReportDetailResponse(report);
    }
}
