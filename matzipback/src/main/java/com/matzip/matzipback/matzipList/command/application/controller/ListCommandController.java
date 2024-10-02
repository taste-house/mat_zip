package com.matzip.matzipback.matzipList.command.application.controller;


import com.matzip.matzipback.matzipList.command.application.dto.CreateListRequest;
import com.matzip.matzipback.matzipList.command.application.dto.DeleteListRequest;
import com.matzip.matzipback.matzipList.command.application.dto.UpdateListRequest;
import com.matzip.matzipback.matzipList.command.application.service.ListCommandService;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessResMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ListCommandController {

    private final ListCommandService listCommandService;

    // 리스트 등록
    @PostMapping("/list")
    public ResponseEntity<SuccessResMessage> createList(@RequestBody CreateListRequest listRequest){

        Long listSeq = listCommandService.createList(listRequest);

        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_SAVE_SUCCESS));
    }

    // 리스트 삭제
    @DeleteMapping("/list")
    public ResponseEntity<SuccessResMessage> deleteList(@Valid @RequestBody DeleteListRequest deleteListRequest){

        listCommandService.deleteList(deleteListRequest);

        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_DELETE_SUCCESS));
    }

    // 리스트 수정
    @PutMapping("/list")
    public ResponseEntity<SuccessResMessage> updateList(@Valid @RequestBody UpdateListRequest updateListRequest){

        Long listSeq = listCommandService.updateList(updateListRequest);

        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_UPDATE_SUCCESS));
    }

}
