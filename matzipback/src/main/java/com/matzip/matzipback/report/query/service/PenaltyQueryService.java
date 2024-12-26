package com.matzip.matzipback.report.query.service;

import com.matzip.matzipback.report.query.dto.PenaltyDTO;
import com.matzip.matzipback.report.query.dto.PenaltyListResponse;
import com.matzip.matzipback.report.query.mapper.PenaltyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PenaltyQueryService {

    private final PenaltyMapper penaltyMapper;

    // 패널티 검색 및 조회
    @Transactional(readOnly = true)
    public PenaltyListResponse getPenalties(Integer page, Integer size, Long penaltyUserSeq, String penaltyStatus, String penaltyType, String penaltyReasonContent) {

        int offset = (page - 1) * size;

        List<PenaltyDTO> penalties = penaltyMapper.selectPenalties(offset, size, penaltyUserSeq, penaltyStatus, penaltyType, penaltyReasonContent);

        Long totalPenalties = penaltyMapper.countPenalties(penaltyUserSeq, penaltyStatus, penaltyType, penaltyReasonContent);

        return PenaltyListResponse.builder()
                .penalties(penalties)
                .currentPage(page)
                .totalPages((int) Math.ceil((double) totalPenalties / size))
                .totalPenalties(totalPenalties)
                .build();
    }
}
