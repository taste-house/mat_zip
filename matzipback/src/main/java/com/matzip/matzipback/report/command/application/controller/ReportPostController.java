package com.matzip.matzipback.report.command.application.controller;

import com.matzip.matzipback.report.command.application.service.ReportPostService;
import com.matzip.matzipback.report.command.domain.aggregate.Report;
import com.matzip.matzipback.report.command.dto.PostCmtReportReqDTO;
import com.matzip.matzipback.report.command.dto.PostCmtReportResMessageDTO;
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
public class ReportPostController {

    private final ReportPostService reportPostService;

    @PostMapping("/post/report")
    public ResponseEntity<PostReportResMessageDTO> createPostReport(@RequestBody PostReportReqDTO postReportReqDTO) {
        Report savedPostReport = reportPostService.savePostReport(postReportReqDTO);

        if (savedPostReport != null) { // 신고 성공
            return ResponseEntity.status(HttpStatus.CREATED).body(new PostReportResMessageDTO(201, ResponseMessage.SAVE_SUCCESS.getMessage(), savedPostReport.getPostSeq()));
        }

        // 신고 실패 -> 중복 신고는 막음
        return ResponseEntity.status(HttpStatus.OK).body(new PostReportResMessageDTO(204, ResponseMessage.SAVE_FAIL.getMessage(), -1L));
    }

    @PostMapping("/postcomment/report")
    public ResponseEntity<PostCmtReportResMessageDTO> createPostCmtReport(@RequestBody PostCmtReportReqDTO postCmtReportDTO) {
        Report savedPostCmtReport = reportPostService.savePostCmtReport(postCmtReportDTO);

        if (savedPostCmtReport != null) { // 신고 성공
            return ResponseEntity.status(HttpStatus.CREATED).body(new PostCmtReportResMessageDTO(201, ResponseMessage.SAVE_SUCCESS.getMessage(), savedPostCmtReport.getPostSeq()));
        }

        // 신고 실패 -> 중복 신고는 막음
        return ResponseEntity.status(HttpStatus.OK).body(new PostCmtReportResMessageDTO(204, ResponseMessage.SAVE_FAIL.getMessage(), -1L));
    }
}
