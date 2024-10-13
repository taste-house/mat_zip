package com.matzip.matzipback.message.query.service;

import com.matzip.matzipback.message.query.dto.MessageShortDTO;
import com.matzip.matzipback.message.query.mapper.MessageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageQueryService {

    private final MessageMapper messageMapper;

    // 쪽지방 목록 조회
    @Transactional(readOnly = true)
    public List<MessageShortDTO> getMessageList(Long userSeq) {

        return messageMapper.getLastMessageList(userSeq);
    }
}
