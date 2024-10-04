package com.matzip.matzipback.matzipList.command.application.controller;


import com.matzip.matzipback.matzipList.command.application.dto.CreateListRequest;
import com.matzip.matzipback.matzipList.command.application.dto.DeleteListRequest;
import com.matzip.matzipback.matzipList.command.application.dto.UpdateListRequest;
import com.matzip.matzipback.matzipList.command.application.service.ListCommandService;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/back/api/vi")
@Tag(name = "List", description = "리스트")
public class ListCommandController {

    private final ListCommandService listCommandService;

    // 리스트 등록
    // whoo 1차 수정
    @PostMapping("/list")
    @Operation(summary = "리스트 등록", description = "리스트를 등록한다.")
    public ResponseEntity<SuccessResMessage> createList(@Valid @RequestBody CreateListRequest listRequest){

        listCommandService.createList(listRequest);

        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_SAVE_SUCCESS));
    }

    // 리스트 삭제
    @DeleteMapping("/list")
    @Operation(summary = "리스트 삭제", description = "리스트를 삭제한다.")
    public ResponseEntity<SuccessResMessage> deleteList(@Valid @RequestBody DeleteListRequest deleteListRequest){

        listCommandService.deleteList(deleteListRequest);

        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_DELETE_SUCCESS));
    }

    // 리스트 수정
    @PutMapping("/list")
    @Operation(summary = "리스트 수정", description = "리스트를 수정한다.")
    public ResponseEntity<SuccessResMessage> updateList(@Valid @RequestBody UpdateListRequest updateListRequest){

        listCommandService.updateList(updateListRequest);

        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_UPDATE_SUCCESS));
    }

}
