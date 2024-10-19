package com.matzip.matzipback.report.command.domain.service;

import com.matzip.matzipback.exception.ErrorCode;
import com.matzip.matzipback.exception.RestApiException;
import com.matzip.matzipback.report.command.domain.aggregate.Report;
import com.matzip.matzipback.report.command.domain.repository.ReportRepository;
import com.matzip.matzipback.report.command.dto.PtAndCmtReportReqDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportDomainService {

    private final ReportRepository reportRepository;
    private final ModelMapper modelMapper;

    // 같은 유저가 똑같은 신고를 하지 못하도록 같은 신고가 있는지를 체크
    public boolean checkReportExists(PtAndCmtReportReqDTO ptAndCmtReportReqDTO) {
         boolean isExistReport = reportRepository.existsByReporterUserSeqAndPostSeq(
                         ptAndCmtReportReqDTO.getReporterUserSeq(),
                         ptAndCmtReportReqDTO.getPostSeq());

         if (isExistReport) throw new RestApiException(ErrorCode.BAD_REQUEST);

         return false;
    }

    public void saveReport(PtAndCmtReportReqDTO postCmtReportReqDTO) {
        // DTO -> Entity
        Report newReport = modelMapper.map(postCmtReportReqDTO, Report.class);
        reportRepository.save(newReport);
    }
}
