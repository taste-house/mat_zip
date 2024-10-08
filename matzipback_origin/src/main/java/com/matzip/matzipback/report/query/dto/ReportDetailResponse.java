package com.matzip.matzipback.report.query.dto;

import lombok.Getter;

@Getter
public class ReportDetailResponse {
    private ReportDTO report;

    public ReportDetailResponse(ReportDTO report) {
        this.report = report;
    }
}
