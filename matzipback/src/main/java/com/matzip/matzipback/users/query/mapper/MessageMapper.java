package com.matzip.matzipback.users.query.mapper;

import com.matzip.matzipback.users.query.dto.MessageChatResDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MessageMapper {
    List<MessageChatResDTO> searchMessageListByUserSeq(long loginUserSeq);

}
