package com.matzip.matzipback.matzipList.command.application.controller;


import com.matzip.matzipback.matzipList.command.application.dto.CreateMatzipRequest;
import com.matzip.matzipback.matzipList.command.application.dto.DeleteMatzipRequest;
import com.matzip.matzipback.matzipList.command.application.dto.UpdateMatzipRequest;
import com.matzip.matzipback.matzipList.command.application.service.MatzipCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class MatzipCommandController {

    private final MatzipCommandService matzipCommandService;

    // 맛집 등록
    @PostMapping("/list/matzip/")
    public ResponseEntity<Void> createMatzip(@Valid @RequestBody CreateMatzipRequest matzipRequest) {

        Long listMatzipSeq = matzipCommandService.createMatzip(matzipRequest);

        return ResponseEntity.created(URI.create("/api/v1/list/matzip" + listMatzipSeq)).build();
    }

    // 맛집 수정
    @PutMapping("/list/matzip/")
    public ResponseEntity<Void> updateMatzip(@Valid @RequestBody UpdateMatzipRequest updateMatzipRequest) {

        Long listSeq = matzipCommandService.updateMatzip(updateMatzipRequest);

        return ResponseEntity.created(URI.create("/api/v1/list/matzip" + listSeq)).build();
    }

    // 맛집 삭제
    @DeleteMapping("/list/matzip/")
    public ResponseEntity<Void> deleteMatzip(@Valid @RequestBody DeleteMatzipRequest deleteMatzipRequest){

        matzipCommandService.deleteMatzip(deleteMatzipRequest);

        return ResponseEntity.noContent().build();
    }

}
