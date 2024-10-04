package com.matzip.matzipuser.report.command.domain.service;

import com.matzip.matzipuser.exception.ErrorCode;
import com.matzip.matzipuser.exception.RestApiException;
import com.matzip.matzipuser.report.command.domain.aggregate.Report;
import com.matzip.matzipuser.report.command.domain.repository.ReportDomainRepository;
import com.matzip.matzipuser.report.command.dto.PtAndCmtReportReqDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportDomainService {

    private final ReportDomainRepository reportDomainRepository;
    private final ModelMapper modelMapper;

    // 같은 유저가 똑같은 신고를 하지 못하도록 같은 신고가 있는지를 체크
    public boolean checkReportExists(PtAndCmtReportReqDTO ptAndCmtReportReqDTO) {
         boolean isExistReport = reportDomainRepository.existsByReporterUserSeqAndPostSeq(
                         ptAndCmtReportReqDTO.getReporterUserSeq(),
                         ptAndCmtReportReqDTO.getPostSeq());

         if (isExistReport) throw new RestApiException(ErrorCode.BAD_REQUEST);

         return false;
    }

    public void saveReport(PtAndCmtReportReqDTO postCmtReportReqDTO) {
        // DTO -> Entity
        Report newReport = modelMapper.map(postCmtReportReqDTO, Report.class);
        reportDomainRepository.save(newReport);
    }
}
