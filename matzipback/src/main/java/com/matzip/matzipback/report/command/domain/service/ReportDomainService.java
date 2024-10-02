package com.matzip.matzipback.report.command.domain.service;

import com.matzip.matzipback.exception.ErrorCode;
import com.matzip.matzipback.exception.RestApiException;
import com.matzip.matzipback.report.command.domain.aggregate.Report;
import com.matzip.matzipback.report.command.domain.repository.ReportDomainRepository;
import com.matzip.matzipback.report.command.dto.PostCmtReportReqDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportDomainService {

    private final ReportDomainRepository reportDomainRepository;
    private final ModelMapper modelMapper;

    // 같은 유저가 똑같은 신고를 하지 못하도록 같은 신고가 있는지를 체크
    public PostCmtReportReqDTO checkReportExists(PostCmtReportReqDTO postCmtReportReqDTO) {
         Report isExistReport = reportDomainRepository.existsByReporterUserSeqAndPostSeq(
                 postCmtReportReqDTO.getReporterUserSeq(),
                 postCmtReportReqDTO.getPostSeq())
                 .orElseThrow(() -> new RestApiException(ErrorCode.BAD_REQUEST));
         return modelMapper.map(isExistReport, PostCmtReportReqDTO.class);
    }

    public void saveReport(PostCmtReportReqDTO postCmtReportReqDTO) {
        // DTO -> Entity
        Report newReport = modelMapper.map(postCmtReportReqDTO, Report.class);
        reportDomainRepository.save(newReport);
    }
}
