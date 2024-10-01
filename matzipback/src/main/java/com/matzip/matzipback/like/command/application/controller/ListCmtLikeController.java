package com.matzip.matzipback.like.command.application.controller;

import com.matzip.matzipback.like.command.application.dto.ListCmtLikeMessageDTO;
import com.matzip.matzipback.like.command.application.dto.ListCmtLikeReqDTO;
import com.matzip.matzipback.like.command.application.service.ListCmtLikeService;
import com.matzip.matzipback.like.command.domain.aggregate.Like;
import com.matzip.matzipback.responsemessage.ResponseMessage;
import jakarta.validation.Valid;
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
public class ListCmtLikeController {

    private final ListCmtLikeService listCmtLikeService;

    @PostMapping("/listCmt/like")
    public ResponseEntity<ListCmtLikeMessageDTO> saveListCmtLike(@Valid @RequestBody ListCmtLikeReqDTO listCmtLikeRequest){
        Like resultLike = listCmtLikeService.saveAndDeleteListCmtLike(listCmtLikeRequest);

        if (resultLike != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ListCmtLikeMessageDTO(201, ResponseMessage.SAVE_SUCCESS.getMessage(), listCmtLikeRequest.getListCommentSeq()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ListCmtLikeMessageDTO(200, ResponseMessage.SAVE_FAIL.getMessage(), listCmtLikeRequest.getListCommentSeq()));
    }

}
