package com.matzip.matzipback.report.command.application.controller;

import com.matzip.matzipback.report.command.application.service.ReportCommandService;
import com.matzip.matzipback.report.command.domain.aggregate.Report;
import com.matzip.matzipback.report.command.dto.PostReportReqDTO;
import com.matzip.matzipback.report.command.dto.PostReportResMessageDTO;
import com.matzip.matzipback.responsemessage.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReportCommandController {

    private final ReportCommandService reportCommandService;

    @PostMapping("/post/report")
    public ResponseEntity<PostReportResMessageDTO> createPostReport(@RequestBody PostReportReqDTO postReportReqDTO) {
        Report savedPostReport = reportCommandService.savePostReport(postReportReqDTO);

        if (savedPostReport != null) { // 신고 성공
            return ResponseEntity.status(HttpStatus.CREATED).body(new PostReportResMessageDTO(201, ResponseMessage.SAVE_SUCCESS.getMessage(), savedPostReport.getPostSeq()));
        }

        // 신고 실패 -> 중복 신고는 막음
        return ResponseEntity.status(HttpStatus.OK).body(new PostReportResMessageDTO(204, ResponseMessage.SAVE_FAIL.getMessage(), -1L));
    }
}