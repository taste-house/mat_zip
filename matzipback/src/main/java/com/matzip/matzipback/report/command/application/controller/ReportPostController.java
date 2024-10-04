package com.matzip.matzipback.report.command.application.controller;

import com.matzip.matzipback.report.command.application.service.ReportPostService;
import com.matzip.matzipback.report.command.dto.PtAndCmtReportReqDTO;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/back/api/vi")
public class ReportPostController {

    private final ReportPostService reportPostService;

    @PostMapping("/post/report")
    public ResponseEntity<SuccessResMessage> createPostReport(@RequestBody PtAndCmtReportReqDTO ptAndCmtReportReqDTO) {
        reportPostService.savePostReport(ptAndCmtReportReqDTO);
        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_SAVE_SUCCESS));
    }
}
