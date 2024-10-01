package com.matzip.matzipback.users.query.service;

import com.matzip.matzipback.users.command.domain.repository.MessageRepository;
import com.matzip.matzipback.users.query.dto.MessageChatResDTO;
import com.matzip.matzipback.users.query.mapper.MessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageQueryService {

    private final MessageMapper messageMapper;

    @Transactional(readOnly = true)
    public List<MessageChatResDTO> searchMessageList(long loginUserSeq) {
        return messageMapper.searchMessageListByUserSeq(loginUserSeq);
    }


}
