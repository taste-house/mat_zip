package com.matzip.matzipback.matzipList.query.controller;

import com.matzip.matzipback.matzipList.query.dto.ListCommentDTO;
import com.matzip.matzipback.matzipList.query.service.ListCommentQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/back/api/v1")
@Tag(name = "List", description = "리스트")
public class ListCommentQueryController {

    private final ListCommentQueryService listCommentQueryService;

    // 리스트에 달린 댓글 조회(삭제되지 않은 댓글만 조회)
    @GetMapping("/listbox/list/listCmt/{listSeq}")
    @Operation(summary = "리스트 댓글 조회", description = "공개된 리스트 댓글을 조회한다.")
    public ResponseEntity<List<ListCommentDTO>> getListComments(@PathVariable("listSeq") Long listSeq){
        return ResponseEntity.ok().body(listCommentQueryService.getListComments(listSeq));
    }
}
