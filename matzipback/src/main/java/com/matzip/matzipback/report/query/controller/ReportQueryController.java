package com.matzip.matzipback.report.query.controller;

import com.matzip.matzipback.common.util.CustomUserUtils;
import com.matzip.matzipback.exception.RestApiException;
import com.matzip.matzipback.report.query.dto.ReportDetailResponse;
import com.matzip.matzipback.report.query.dto.ReportListResponse;
import com.matzip.matzipback.report.query.service.ReportQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.matzip.matzipback.exception.ErrorCode.FORBIDDEN_ACCESS;
import static com.matzip.matzipback.exception.ErrorCode.UNAUTHORIZED_REQUEST;

@RestController
@RequiredArgsConstructor
@RequestMapping("/back/api/v1")
@Tag(name = "Report", description = "신고")
public class ReportQueryController {

    private final ReportQueryService reportQueryService;

    @GetMapping("/report")
    @Operation(summary = "신고 검색 및 조회", description = "신고를 검색 및 조회한다.")
    public ResponseEntity<ReportListResponse> getReports(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) Long reporterUserSeq,
            @RequestParam(required = false) Long reportedUserSeq,
            @RequestParam(required = false) String reportStatus,
            @RequestParam(required = false) Long category,
            @RequestParam(required = false) Long sequence) {

        try { if (CustomUserUtils.getCurrentUserAuthorities().iterator().next().getAuthority().equals("admin")) {
                return ResponseEntity.ok(reportQueryService.getReports(page, size, reporterUserSeq, reportedUserSeq, reportStatus, category, sequence));
            } else { throw new RestApiException(FORBIDDEN_ACCESS); }
        } catch (NullPointerException e) { throw new RestApiException(UNAUTHORIZED_REQUEST); }
    }

    @GetMapping("/report/{reportSeq}")
    @Operation(summary = "신고 상세 조회", description = "특정 신고를 조회한다.")
    public ResponseEntity<ReportDetailResponse> getReport(
            @PathVariable Long reportSeq) {

        try { if (CustomUserUtils.getCurrentUserAuthorities().iterator().next().getAuthority().equals("admin")) {
            return ResponseEntity.ok(reportQueryService.getReport(reportSeq));
        } else { throw new RestApiException(FORBIDDEN_ACCESS); }
        } catch (NullPointerException e) { throw new RestApiException(UNAUTHORIZED_REQUEST); }
    }
}
