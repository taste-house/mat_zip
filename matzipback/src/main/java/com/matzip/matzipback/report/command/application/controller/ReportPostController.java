package com.matzip.matzipback.report.command.application.controller;

import com.matzip.matzipback.report.command.application.service.ReportPostService;
import com.matzip.matzipback.report.command.domain.aggregate.Report;
import com.matzip.matzipback.report.command.dto.PostCmtReportReqDTO;
import com.matzip.matzipback.report.command.dto.PostCmtReportResMessageDTO;
import com.matzip.matzipback.report.command.dto.PostReportReqDTO;
import com.matzip.matzipback.report.command.dto.PostReportResMessageDTO;
import com.matzip.matzipback.responsemessage.ResponseMessage;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReportPostController {

    private final ReportPostService reportPostService;

    @PostMapping("/post/report")
    public ResponseEntity<SuccessResMessage> createPostReport(@RequestBody PostReportReqDTO postReportReqDTO) {
        reportPostService.savePostReport(postReportReqDTO);
        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_SAVE_SUCCESS));
    }
}
