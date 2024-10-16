package com.matzip.matzipback.report.query.mapper;

import com.matzip.matzipback.report.query.dto.PenaltyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PenaltyMapper {

    List<PenaltyDTO> selectPenalties(
            @Param("offset") int offset,
            @Param("size") int size,
            @Param("penaltyUserSeq") Long penaltyUserSeq,
            @Param("penaltyStatus") String penaltyStatus,
            @Param("penaltyType") String penaltyType,
            @Param("penaltyReasonContent") String penaltyReasonContent
    );

    Long countPenalties(
            @Param("penaltyUserSeq") Long penaltyUserSeq,
            @Param("penaltyStatus") String penaltyStatus,
            @Param("penaltyType") String penaltyType,
            @Param("penaltyReasonContent") String penaltyReasonContent
    );
}
