package com.matzip.matzipback.users.query.mapper;

import com.matzip.matzipback.users.query.dto.MessageChatResDTO;
import com.matzip.matzipback.users.query.dto.MessageDetailResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMapper {
    List<MessageChatResDTO> searchMessageListByUserSeq(long loginUserSeq);

    List<MessageDetailResDTO> searchMessageDetail(@Param("userSeq") long userSeq, @Param("partnerSeq") long partnerSeq);
}
