package com.matzip.matzipback.message.command.domain.service;

import com.matzip.matzipback.exception.ErrorCode;
import com.matzip.matzipback.exception.RestApiException;
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

    // 쪽지 삭제하기
    public void deleteMessage(Long userSeq, Long messageSeq) {

        Messages foundMessage = messageDomainRepository.findById(messageSeq)
                .orElseThrow(() -> new RestApiException(ErrorCode.NOT_FOUND));

        // 쪽지를 보낸 사람이 로그인한 회원이면
        if (foundMessage.getMessageSendUserSeq() == userSeq) {
            // 쪽지를 보낸 사람이 지웠다는 컬럼을 Y로 바꾼다.
            foundMessage.updateSenderDeletedYn();
        } else if (foundMessage.getMessageReceiveUserSeq() == userSeq) { // 쪽지를 받은 사람이 로그인한 회원이면
            // 쪽지를 받은 사람이 지웠다는 컬럼을 Y로 바꾼다.
            foundMessage.updateRecipientDeletedYn();
        } else {
            throw new RestApiException(ErrorCode.UNAUTHORIZED_REQUEST);
        }
    }
}
