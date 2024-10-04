package com.matzip.matzipback.matzipList.command.application.controller;

import com.matzip.matzipback.common.util.CustomUserUtils;
import com.matzip.matzipback.exception.RestApiException;
import com.matzip.matzipback.matzipList.command.application.dto.CreateListCmtRequest;
import com.matzip.matzipback.matzipList.command.application.dto.DeleteListCmtRequset;
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

import java.net.URI;

import static com.matzip.matzipback.exception.ErrorCode.FORBIDDEN_ACCESS;
import static com.matzip.matzipback.exception.ErrorCode.UNAUTHORIZED_REQUEST;

@RestController
@RequiredArgsConstructor
@RequestMapping("/back/api/vi")
@Tag(name = "List", description = "리스트")
public class ListCmtCommandController {

    private final ListCmtCommandService listCmtCommandService;

    // 리스트 댓글 등록
    @PostMapping("/list/comment")
    @Operation(summary = "리스트 댓글 등록", description = "리스트 댓글을 등록한다.")
    public ResponseEntity<SuccessResMessage> createListCmt(@RequestBody CreateListCmtRequest listCmtRequest){

        try { if (CustomUserUtils.getCurrentUserAuthorities().iterator().next().getAuthority().equals("user")) {

            Long listCmtSeq = listCmtCommandService.createListCmt(listCmtRequest);

            return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_SAVE_SUCCESS));
        } else { throw new RestApiException(FORBIDDEN_ACCESS); }

        } catch (NullPointerException e) { throw new RestApiException(UNAUTHORIZED_REQUEST); }
    }

    // 리스트 댓글 삭제
    @DeleteMapping("/list/comment")
    @Operation(summary = "리스트 댓글 삭제", description = "리스트 댓글을 삭제한다.")
    public ResponseEntity<SuccessResMessage> deleteListCmt(@Valid @RequestBody DeleteListCmtRequset deleteListCmtRequset){

        try { if (CustomUserUtils.getCurrentUserAuthorities().iterator().next().getAuthority().equals("user")) {

            listCmtCommandService.deleteListCmt(deleteListCmtRequset);

            return ResponseEntity
                    .ok(new SuccessResMessage(SuccessCode.BASIC_DELETE_SUCCESS));
        } else { throw new RestApiException(FORBIDDEN_ACCESS); }

        } catch (NullPointerException e) { throw new RestApiException(UNAUTHORIZED_REQUEST); }
    }

    // 리스트 댓글 수정
    @Operation(summary = "리스트 댓글 수정", description = "리스트 댓글을 수정한다.")
    @PutMapping("/list/comment")
    public ResponseEntity<Void> updateListCmt(@Valid @RequestBody UpdateListCmtRequest updateListCmtRequest){
        Long listCmtSeq = listCmtCommandService.updateListCmt(updateListCmtRequest);

        return ResponseEntity.created(URI.create("/back/api/vi/list/comment" + listCmtSeq)).build();
    }
}
