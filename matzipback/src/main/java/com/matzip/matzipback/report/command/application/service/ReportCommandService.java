package com.matzip.matzipback.report.command.application.service;

import com.matzip.matzipback.report.command.domain.aggregate.Report;
import com.matzip.matzipback.report.command.domain.repository.ReportDomainRepository;
import com.matzip.matzipback.report.command.dto.PostReportResReqDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportCommandService {

    private final ReportDomainRepository reportDomainRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public boolean savePostReport(PostReportResReqDTO postReportResReqDTO) {

        Report postReport = modelMapper.map(postReportResReqDTO, Report.class);

        Report save = reportDomainRepository.save(postReport);

        return save != null;
    }
}
