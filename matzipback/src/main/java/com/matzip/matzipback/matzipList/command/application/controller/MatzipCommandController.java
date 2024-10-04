package com.matzip.matzipback.matzipList.command.application.controller;


import com.matzip.matzipback.matzipList.command.application.dto.CreateMatzipRequest;
import com.matzip.matzipback.matzipList.command.application.dto.DeleteMatzipRequest;
import com.matzip.matzipback.matzipList.command.application.dto.UpdateMatzipRequest;
import com.matzip.matzipback.matzipList.command.application.service.MatzipCommandService;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/back/api/vi/")
@Tag(name = "List", description = "리스트")
public class MatzipCommandController {

    private final MatzipCommandService matzipCommandService;

    // 맛집 등록
    @PostMapping("/list/matzip/")
    @Operation(summary = "리스트 맛집 등록", description = "내 리스트에 맛집을 등록한다.")
    public ResponseEntity<SuccessResMessage> createMatzip(@Valid @RequestBody CreateMatzipRequest matzipRequest) {

        matzipCommandService.createMatzip(matzipRequest);

        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_SAVE_SUCCESS));
    }

    // 맛집 수정
    @PutMapping("/list/matzip/")
    @Operation(summary = "리스트 맛집 수정", description = "내 리스트에 등록된 맛집을 수정한다.")
    public ResponseEntity<SuccessResMessage> updateMatzip(@Valid @RequestBody UpdateMatzipRequest updateMatzipRequest) {

        matzipCommandService.updateMatzip(updateMatzipRequest);

        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_UPDATE_SUCCESS));
    }

    // 맛집 삭제
    @DeleteMapping("/list/matzip/")
    @Operation(summary = "리스트 맛집 삭제", description = "내 리스트에 맛집을 삭제한다.")
    public ResponseEntity<SuccessResMessage> deleteMatzip(@Valid @RequestBody DeleteMatzipRequest deleteMatzipRequest){

        matzipCommandService.deleteMatzip(deleteMatzipRequest);

        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_DELETE_SUCCESS));
    }

}
