package com.matzip.matzipback.like.command.application.controller;


import com.matzip.matzipback.like.command.application.dto.ListLikeMessageReqDto;
import com.matzip.matzipback.like.command.application.dto.ListLikeReqDTO;
import com.matzip.matzipback.like.command.application.service.ListLikeService;
import com.matzip.matzipback.like.command.domain.aggregate.Like;
import com.matzip.matzipback.responsemessage.ResponseMessage;
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
public class ListLikeController {

    private final ListLikeService listLikeService;

    @PostMapping("/list/like")
    public ResponseEntity<ListLikeMessageReqDto> saveListLike(@RequestBody ListLikeReqDTO listLikeRequest){
        Like resultLike = listLikeService.saveListLike(listLikeRequest);

        if (resultLike != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ListLikeMessageReqDto(201, ResponseMessage.SAVE_SUCCESS.getMessage(), resultLike.getListSeq()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ListLikeMessageReqDto(200, ResponseMessage.SAVE_FAIL.getMessage(), -1));
    }


}
