package com.matzip.matzipback.matzipList.command.application.controller;

import com.matzip.matzipback.exception.ErrorCode;
import com.matzip.matzipback.exception.ErrorResponse;
import com.matzip.matzipback.matzipList.command.application.dto.MatzipCollectReq;
import com.matzip.matzipback.matzipList.command.application.service.MatzipCollectService;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.service.GenericResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MatzipCollectCommandController {

    private final MatzipCollectService matzipCollectService;
    private final GenericResponseService responseBuilder;

    // 맛집 가져오기(등록)
    @PostMapping("/list/matzip/collect")
    public ResponseEntity<SuccessResMessage> collectMatzip(@Valid @RequestBody MatzipCollectReq matzipCollectReq){

        Long listMatzipSeq = matzipCollectService.collectMatzip(matzipCollectReq);

        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_SAVE_SUCCESS));
    }
}
