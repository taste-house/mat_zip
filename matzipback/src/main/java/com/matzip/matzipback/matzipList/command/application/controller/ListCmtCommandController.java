package com.matzip.matzipback.matzipList.command.application.controller;

import com.matzip.matzipback.matzipList.command.application.dto.CreateListCmtRequest;
import com.matzip.matzipback.matzipList.command.application.dto.DeleteListCmtRequset;
import com.matzip.matzipback.matzipList.command.application.dto.UpdateListCmtRequest;
import com.matzip.matzipback.matzipList.command.application.service.ListCmtCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "List", description = "리스트")
public class ListCmtCommandController {

    private final ListCmtCommandService listCmtCommandService;

    // 리스트 댓글 등록
    @PostMapping("/list/comment")
    @Operation(summary = "리스트 댓글 등록", description = "리스트 댓글을 등록한다.")
    public ResponseEntity<Void> createListCmt(@RequestBody CreateListCmtRequest listCmtRequest){

        Long listCmtSeq = listCmtCommandService.createListCmt(listCmtRequest);

        return ResponseEntity.created(URI.create("/api/v1/list/comment" + listCmtSeq)).build();
    }

    // 리스트 댓글 삭제
    @DeleteMapping("/list/comment")
    @Operation(summary = "리스트 댓글 삭제", description = "리스트 댓글을 삭제한다.")
    public ResponseEntity<Void> deleteListCmt(@Valid @RequestBody DeleteListCmtRequset deleteListCmtRequset){

        listCmtCommandService.deleteListCmt(deleteListCmtRequset);

        return ResponseEntity.noContent().build();
    }

    // 리스트 댓글 수정
    @Operation(summary = "리스트 댓글 수정", description = "리스트 댓글을 수정한다.")
    @PutMapping("/list/comment")
    public ResponseEntity<Void> updateListCmt(@Valid @RequestBody UpdateListCmtRequest updateListCmtRequest){
        Long listCmtSeq = listCmtCommandService.updateListCmt(updateListCmtRequest);

        return ResponseEntity.created(URI.create("/api/v1/list/comment" + listCmtSeq)).build();
    }
}
