package com.matzip.matzipback.report.command.application.controller;

import com.matzip.matzipback.exception.ErrorCode;
import com.matzip.matzipback.exception.RestApiException;
import com.matzip.matzipback.report.command.application.service.ReportMessageService;
import com.matzip.matzipback.report.command.dto.SaveMessageReportDTO;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/back/api/v1")
public class ReportMessageController {

    private final ReportMessageService reportMessageService;

    @PostMapping("/message/report")
    public ResponseEntity<SuccessResMessage> createMessageReport(
            @Valid @RequestBody SaveMessageReportDTO saveMessageReportDTO) {

        boolean result = reportMessageService.createMessageReport(saveMessageReportDTO);

        if (!result) {
            throw new RestApiException(ErrorCode.REPORT_FAIL);
        }

        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.REPOST_SUCCESS));
    }
}
