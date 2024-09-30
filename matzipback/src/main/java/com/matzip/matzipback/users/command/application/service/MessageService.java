package com.matzip.matzipback.users.command.application.service;

import com.matzip.matzipback.common.util.CustomUserUtils;
import com.matzip.matzipback.users.command.domain.service.MessageDomainService;
import com.matzip.matzipback.users.command.dto.MessageReqDTO;
import com.matzip.matzipback.users.command.dto.MessageResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageDomainService messageDomainService;

    @Transactional
    public MessageResDTO createMessage(MessageReqDTO messageReqDTO, long loginUserSeq) {
//        long currentUserSeq = CustomUserUtils.getCurrentUserSeq();
        return messageDomainService.createdMessage(messageReqDTO, loginUserSeq);
    }
}
