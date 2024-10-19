package com.matzip.matzipback.report.command.domain.service;

import com.matzip.matzipback.report.command.domain.aggregate.Report;
import com.matzip.matzipback.report.command.domain.repository.ReportRepository;
import com.matzip.matzipback.report.command.dto.SaveMessageReportDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DomainReportMessageService {

    private final ReportRepository reportRepository;
    private final ModelMapper modelMapper;

    // 메시지 신고하기
    public boolean saveMessageReport(SaveMessageReportDTO saveMessageReportDTO) {

        Report newReport = modelMapper.map(saveMessageReportDTO, Report.class);

        return reportRepository.save(newReport) != null;
    }

}
