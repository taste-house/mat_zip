package com.matzip.matzipback.message.command.application.controller;

import com.matzip.matzipback.message.command.application.dto.SendMessageDTO;
import com.matzip.matzipback.message.command.application.service.MessageService;
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
@RequestMapping("/back/api/v1")
@Tag(name = "Message", description = "쪽지")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/message")
    @Operation(summary = "쪽지 보내기", description = "쪽지를 보낸다.")
    public ResponseEntity<SuccessResMessage> sendMessage(@Valid @RequestBody SendMessageDTO sendMessageDTO) {

        messageService.sendMessage(sendMessageDTO);
        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.BASIC_SAVE_SUCCESS));
    }

    @DeleteMapping("/message")
    @Operation(summary = "쪽지 삭제", description = "쪽지를 삭제한다.")
    public ResponseEntity<SuccessResMessage> deleteMessage(@RequestParam("userseq") Long userSeq,
                                                           @RequestParam("messageseq") Long messageSeq) {

        messageService.deleteMessage(userSeq, messageSeq);

        return ResponseEntity.ok(new SuccessResMessage(SuccessCode.LIKE_DELETE_SUCCESS));
    }




}
