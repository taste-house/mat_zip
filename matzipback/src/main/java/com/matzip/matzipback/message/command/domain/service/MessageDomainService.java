package com.matzip.matzipback.message.command.domain.service;

import com.matzip.matzipback.message.command.application.dto.SendMessageDTO;
import com.matzip.matzipback.message.command.domain.aggregate.Messages;
import com.matzip.matzipback.message.command.domain.repository.MessageDomainRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageDomainService {

    private final MessageDomainRepository messageDomainRepository;
    private final ModelMapper modelMapper;

    // 쪽지 보내기 = 쪽지 저장하기
    public void sendMessage(SendMessageDTO sendMessageDTO) {

        Messages createdMessage = modelMapper.map(sendMessageDTO, Messages.class);

        messageDomainRepository.save(createdMessage);
    }
}
