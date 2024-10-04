package com.matzip.matzipback.report.command.application.controller;

import com.matzip.matzipback.report.command.application.service.ReportListService;
import com.matzip.matzipback.report.command.domain.aggregate.Report;
import com.matzip.matzipback.report.command.dto.ListCmtReportReqDTO;
import com.matzip.matzipback.report.command.dto.ListCmtReportReqMessageDTO;
import com.matzip.matzipback.report.command.dto.ListReportReqDTO;
import com.matzip.matzipback.report.command.dto.ListReportReqMessageDTO;
import com.matzip.matzipback.responsemessage.ResponseMessage;
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
public class ReportListController {

    private final ReportListService reportListService;

    // 리스트 신고 등록
    @PostMapping("/list/report")
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
    public ResponseEntity<ListCmtReportReqMessageDTO> createListCmtReport(@RequestBody ListCmtReportReqDTO listCmtReportReqDTO){

        Report saveListCmtReport = reportListService.saveListCmtReport(listCmtReportReqDTO);

        String redirectURI = "/back/api/vi/listCmt/report" + saveListCmtReport.getListCommentSeq();

        if(saveListCmtReport != null){
            return ResponseEntity.status(HttpStatus.CREATED).location(URI.create(redirectURI)).build();
        }


        return ResponseEntity.status(HttpStatus.OK).body(new ListCmtReportReqMessageDTO(201, ResponseMessage.SAVE_FAIL.getMessage(), listCmtReportReqDTO.getListCommentSeq()));
    }
}
