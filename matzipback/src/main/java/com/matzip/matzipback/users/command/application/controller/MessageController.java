package com.matzip.matzipback.users.command.application.controller;

import com.matzip.matzipback.responsemessage.ResponseMessage;
import com.matzip.matzipback.users.command.application.service.MessageService;
import com.matzip.matzipback.users.command.dto.MessageReqDTO;
import com.matzip.matzipback.users.command.dto.MessageResDTO;
import com.matzip.matzipback.users.command.dto.MessageResMessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MessageController {

    private final MessageService messageService;

    // 쪽지 보내기
    @PostMapping("/messageList/{userSeq}")
    public ResponseEntity<MessageResMessageDTO> sendMessage(@PathVariable long userSeq, @RequestBody MessageReqDTO messageReqDTO) {
        // 로그인 한 회원
//        long loginUserSeq = CustomUserUtils.getCurrentUserSeq();

        // 접근하려는 url 이 개인적인 url 인 경우
//        if (loginUserSeq == messageReqDTO.getMessageReceiveUserSeq() || loginUserSeq != userSeq) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }

        // 로그인 한 회원
        long loginUserSeq = 2L;


        MessageResDTO createdMessage = messageService.createMessage(messageReqDTO, loginUserSeq);

        return ResponseEntity.ok(new MessageResMessageDTO(HttpStatus.OK.value(), ResponseMessage.SAVE_SUCCESS.getMessage(), List.of(createdMessage)));
    }
}
