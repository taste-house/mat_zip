package com.matzip.matzipback.report.command.application.controller;

import com.matzip.matzipback.report.command.application.service.ReportListService;
import com.matzip.matzipback.report.command.domain.aggregate.Report;
import com.matzip.matzipback.report.command.dto.ListCmtReportReqDTO;
import com.matzip.matzipback.report.command.dto.ListCmtReportReqMessageDTO;
import com.matzip.matzipback.report.command.dto.ListReportReqDTO;
import com.matzip.matzipback.report.command.dto.ListReportReqMessageDTO;
import com.matzip.matzipback.responsemessage.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/back/api/vi")
@Tag(name = "Report", description = "신고")
public class ReportListController {

    private final ReportListService reportListService;

    // 리스트 신고 등록
    @PostMapping("/list/report")
    @Operation(summary = "리스트 신고 등록", description = "리스트 신고를 등록한다.")
    public ResponseEntity<ListReportReqMessageDTO> createListReport(@RequestBody ListReportReqDTO listReportReqDTO){

        Report saveListReport = reportListService.saveListReport(listReportReqDTO);

        String redirectURI = "/back/api/vi/list/report" + saveListReport.getListSeq();

        if(saveListReport != null){
            return ResponseEntity.status(HttpStatus.CREATED).location(URI.create(redirectURI)).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ListReportReqMessageDTO(201, ResponseMessage.SAVE_FAIL.getMessage(), listReportReqDTO.getListSeq()));
    }

    // 리스트 댓글 신고 등록
    @PostMapping("/listCmt/report")
    @Operation(summary = "리스트 댓글 신고 등록", description = "리스트 댓글 신고를 등록한다.")
    public ResponseEntity<ListCmtReportReqMessageDTO> createListCmtReport(@RequestBody ListCmtReportReqDTO listCmtReportReqDTO){

        Report saveListCmtReport = reportListService.saveListCmtReport(listCmtReportReqDTO);

        String redirectURI = "/back/api/vi/listCmt/report" + saveListCmtReport.getListCommentSeq();

        if(saveListCmtReport != null){
            return ResponseEntity.status(HttpStatus.CREATED).location(URI.create(redirectURI)).build();
        }


        return ResponseEntity.status(HttpStatus.OK).body(new ListCmtReportReqMessageDTO(201, ResponseMessage.SAVE_FAIL.getMessage(), listCmtReportReqDTO.getListCommentSeq()));
    }
}
