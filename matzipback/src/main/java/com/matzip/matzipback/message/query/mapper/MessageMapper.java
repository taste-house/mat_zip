package com.matzip.matzipback.message.query.mapper;

import com.matzip.matzipback.message.query.dto.MessageDetailDTO;
import com.matzip.matzipback.message.query.dto.MessageShortDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMapper {

    // 쪽지방 목록 조회
    List<MessageShortDTO> getLastMessageList(Long userSeq);

    List<MessageDetailDTO> getMessageList(@Param("userSeq") Long userSeq,
                                          @Param("partner") Long partner);

    void updateReadYn(@Param("userSeq") Long userSeq,
                      @Param("partner") Long partner);
}
