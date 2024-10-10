package com.matzip.matzipback.report.command.application.controller;

import com.matzip.matzipback.report.command.application.service.ReportPostCmtService;
import com.matzip.matzipback.report.command.dto.PtAndCmtReportReqDTO;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/back/api/v1")
@Tag(name = "Report", description = "신고")
public class ReportPostCmtController {

    private final ReportPostCmtService reportPostCmtService;

    @PostMapping("/postcomment/report")
    @Operation(summary = "게시글 댓글 신고 등록", description = "게시글 댓글 신고를 등록한다.")
    public ResponseEntity<SuccessResMessage> createPostCmtReport(@RequestBody PtAndCmtReportReqDTO postCmtReportDTO) {
        reportPostCmtService.savePostCmtReport(postCmtReportDTO);
        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_SAVE_SUCCESS));
    }

}
