package com.matzip.matzipback.message.query.controller;

import com.matzip.matzipback.message.query.dto.MessageDetailDTO;
import com.matzip.matzipback.message.query.dto.MessageShortDTO;
import com.matzip.matzipback.message.query.service.MessageQueryService;
import com.matzip.matzipback.responsemessage.SuccessCode;
import com.matzip.matzipback.responsemessage.SuccessSearchResMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/back/api/v1")
@Tag(name = "Message", description = "쪽지")
public class MessageQueryController {

    private final MessageQueryService messageQueryService;

    // 쪽지방 목록 조회
    @GetMapping("/messageList/{userSeq}")
    @Operation(summary = "쪽지방 목록 조회", description = "쪽지방 목록을 조회한다.")
    public ResponseEntity<SuccessSearchResMessage<?>> getMessageList(@PathVariable("userSeq") Long userSeq) {

        List<MessageShortDTO> messageRoomList = messageQueryService.getMessageList(userSeq);

        return ResponseEntity.ok(new SuccessSearchResMessage<>(SuccessCode.BASIC_GET_SUCCESS, messageRoomList));
    }

    // 쪽지방 상세 조회
    @GetMapping("/messageList")
    @Operation(summary = "쪽지방 상세 조회", description = "쪽지방을 상세 조회한다.")
    public ResponseEntity<SuccessSearchResMessage<?>> getMessageList(
            @RequestParam("userseq") Long userSeq,
            @RequestParam("partner") Long partner) {

        List<MessageDetailDTO> messageList = messageQueryService.getMessageRoomDetail(userSeq, partner);
        return ResponseEntity.ok(new SuccessSearchResMessage<>(SuccessCode.BASIC_GET_SUCCESS, messageList));
    }
}
