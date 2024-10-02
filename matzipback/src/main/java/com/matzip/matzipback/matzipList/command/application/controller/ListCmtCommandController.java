package com.matzip.matzipback.matzipList.command.application.controller;

import com.matzip.matzipback.matzipList.command.application.dto.CreateListCmtRequest;
import com.matzip.matzipback.matzipList.command.application.dto.UpdateListCmtRequest;
import com.matzip.matzipback.matzipList.command.application.service.ListCmtCommandService;
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
@RequestMapping("/api/v1")
@Tag(name = "ListComment", description = "리스트 댓글")
public class ListCmtCommandController {

    private final ListCmtCommandService listCmtCommandService;

    // 1차 수정 완료 - 창윤
    // 리스트 댓글 등록
    @PostMapping("/list/comment")
    @Operation(summary = "리스트 댓글 등록", description = "리스트 댓글을 등록한다.")
    public ResponseEntity<SuccessResMessage> createListCmt(@RequestBody CreateListCmtRequest createListCmtRequest){

        listCmtCommandService.createListCmt(createListCmtRequest);

        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_SAVE_SUCCESS));
    }

    // 1차 수정 완료 - 창윤
    // 리스트 댓글 삭제
    @DeleteMapping("/list/comment/{listCommentSeq}")
    public ResponseEntity<SuccessResMessage> deleteListCmt(@PathVariable(value = "listCommentSeq") Long listCommentSeq){

        listCmtCommandService.deleteListCmt(listCommentSeq);

        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_DELETE_SUCCESS));
    }

    // 1차 수정 완료 - 창윤
    // 리스트 댓글 수정
    @PutMapping("/list/comment")
    public ResponseEntity<SuccessResMessage> updateListCmt(@Valid @RequestBody UpdateListCmtRequest updateListCmtRequest){

        listCmtCommandService.updateListCmt(updateListCmtRequest);

        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_UPDATE_SUCCESS));
    }
}
