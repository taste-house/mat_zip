package com.matzip.matzipback.users.command.domain.service;

import com.matzip.matzipback.users.command.domain.aggregate.Messages;
import com.matzip.matzipback.users.command.domain.repository.MessageRepository;
import com.matzip.matzipback.users.command.dto.MessageReqDTO;
import com.matzip.matzipback.users.command.dto.MessageResDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageDomainService {

    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;

    public MessageResDTO createdMessage(MessageReqDTO messageReqDTO, long loginUserSeq) {
        messageReqDTO.setMessageSendUserSeq(loginUserSeq);
        Messages createdMessage = modelMapper.map(messageReqDTO, Messages.class);
        Messages savedMessage = messageRepository.save(createdMessage);
        return modelMapper.map(savedMessage, MessageResDTO.class);
    }
}
