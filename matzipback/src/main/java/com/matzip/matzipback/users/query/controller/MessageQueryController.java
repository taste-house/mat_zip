package com.matzip.matzipback.users.query.controller;

import com.matzip.matzipback.responsemessage.ResponseMessage;
import com.matzip.matzipback.users.query.dto.MessageChatResDTO;
import com.matzip.matzipback.users.query.dto.SearchMessageListMessageDTO;
import com.matzip.matzipback.users.query.service.MessageQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MessageQueryController {

    private final MessageQueryService messageQueryService;

    // 쪽지 대화방 리스트 조회
    @GetMapping("/messageList/{userSeq}")
    public ResponseEntity<SearchMessageListMessageDTO> searchMessageList(@PathVariable long userSeq) {

        // 로그인한 회원
//        long currentUserSeq = CustomUserUtils.getCurrentUserSeq();
//
//        if (userSeq != currentUserSeq) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }

        List<MessageChatResDTO> messageChatResDTOS = messageQueryService.searchMessageList(userSeq);

        SearchMessageListMessageDTO searchMessageListMessageDTO = new SearchMessageListMessageDTO();
        searchMessageListMessageDTO.setCode(HttpStatus.OK.value());
        searchMessageListMessageDTO.setMessage(ResponseMessage.FOUND.getMessage());
        searchMessageListMessageDTO.setMessageSendUserSeq(userSeq);
        searchMessageListMessageDTO.setMessageChats(messageChatResDTOS);

        return ResponseEntity.ok(searchMessageListMessageDTO);
    }
}
