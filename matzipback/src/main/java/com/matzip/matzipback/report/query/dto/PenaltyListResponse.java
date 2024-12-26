package com.matzip.matzipback.report.query.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PenaltyListResponse {
    private List<PenaltyDTO> penalties;
    private int currentPage;
    private int totalPages;
    private Long totalPenalties;
}
