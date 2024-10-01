package com.matzip.matzipback.report.command.application.controller;

import com.matzip.matzipback.report.command.application.service.ReportListService;
import com.matzip.matzipback.report.command.domain.aggregate.Report;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReportListController {

    private final ReportListService reportListService;

    // 리포트 신고 등록
    @PostMapping("/list/report")
    public ResponseEntity<ListReportReqMessageDTO> createListReport(@RequestBody ListReportReqDTO listReportReqDTO){

        Report saveListReport = reportListService.saveListReport(listReportReqDTO);

        if(saveListReport != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(new ListReportReqMessageDTO(201, ResponseMessage.SAVE_SUCCESS.getMessage(), listReportReqDTO.getListSeq()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ListReportReqMessageDTO(200, ResponseMessage.SAVE_FAIL.getMessage(), listReportReqDTO.getListSeq()));
    }
}
