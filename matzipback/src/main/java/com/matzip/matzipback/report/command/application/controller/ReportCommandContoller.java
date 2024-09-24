package com.matzip.matzipback.report.command.application.controller;

import com.matzip.matzipback.report.command.application.service.ReportCommandService;
import com.matzip.matzipback.report.command.dto.PostReportResDTO;
import com.matzip.matzipback.report.command.dto.PostReportResReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReportCommandContoller {

    private final ReportCommandService reportCommandService;

    // 게시글 신고
    @PostMapping("/post/report")
    public ResponseEntity<PostReportResDTO> postReportSave(@RequestBody PostReportResReqDTO postReportResReqDTO) {
        boolean result = reportCommandService.savePostReport(postReportResReqDTO);

        if (result) return ResponseEntity.status(HttpStatus.CREATED).body(new PostReportResDTO(1, "게시글 신고 성공"));

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new PostReportResDTO(0, "게시글 신고 실패"));
    }
}
