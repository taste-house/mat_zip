package com.matzip.matzipback.message.command.application.service;

import com.matzip.matzipback.common.util.CustomUserUtils;
import com.matzip.matzipback.exception.ErrorCode;
import com.matzip.matzipback.exception.RestApiException;
import com.matzip.matzipback.message.command.application.dto.SendMessageDTO;
import com.matzip.matzipback.message.command.domain.service.MessageDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageDomainService messageDomainService;

    // 쪽지 보내기 기능
    @Transactional
    public void sendMessage(SendMessageDTO sendMessageDTO) {

        // 로그인 한 유저
        Long loginUserSeq = CustomUserUtils.getCurrentUserSeq();

        // 로그인한 유저랑 쪽지를 보내는 유저가 다르면 에러 발생
        if (loginUserSeq != sendMessageDTO.getMessageSendUserSeq()) {
            throw new RestApiException(ErrorCode.UNAUTHORIZED_REQUEST);
        }

        messageDomainService.sendMessage(sendMessageDTO);
    }
}
