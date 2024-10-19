package com.matzip.matzipback.report.command.application.service;

import com.matzip.matzipback.common.util.CustomUserUtils;
import com.matzip.matzipback.exception.ErrorCode;
import com.matzip.matzipback.exception.RestApiException;
import com.matzip.matzipback.report.command.domain.service.DomainReportMessageService;
import com.matzip.matzipback.report.command.dto.SaveMessageReportDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportMessageService {

    private final DomainReportMessageService domainReportMessageService;

    @Transactional
    public boolean createMessageReport(SaveMessageReportDTO saveMessageReportDTO) {

        Long loginUserSeq = CustomUserUtils.getCurrentUserSeq();

        // 로그인한 회원이 아니면
        if (loginUserSeq != saveMessageReportDTO.getReporterUserSeq()) {
            throw new RestApiException(ErrorCode.UNAUTHORIZED_REQUEST);
        }

        return domainReportMessageService.saveMessageReport(saveMessageReportDTO);
    }
}
