package com.matzip.matzipuser.report.query.mapper;

import com.matzip.matzipuser.report.query.dto.ReportDTO;
import com.matzip.matzipuser.report.query.dto.ReasonDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportMapper {

    List<ReportDTO> selectReports(
            @Param("offset") int offset,
            @Param("limit") int limit,
            @Param("reporterUserSeq") Long reporterUserSeq,
            @Param("reportedUserSeq") Long reportedUserSeq,
            @Param("reportStatus") String reportStatus,
            @Param("category") Long category,
            @Param("sequence") Long sequence);

    List<ReasonDTO> selectReportReasons(
            @Param("reportSeq") Long reportSeq);

    long countReports(
            @Param("reporterUserSeq") Long reporterUserSeq,
            @Param("reportedUserSeq") Long reportedUserSeq,
            @Param("reportStatus") String reportStatus,
            @Param("category") Long category,
            @Param("sequence") Long sequence);

    ReportDTO selectReportBySeq(
            @Param("reportSeq") Long reportSeq);
}
