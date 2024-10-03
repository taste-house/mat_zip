package com.matzip.matzipback.report.query.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @Builder
public class ReportListResponse {
    private List<ReportDTO> reports;
    private int currentPage;
    private int totalPages;
    private long totalItems;
}
