package com.matzip.matzipback.users.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SearchMessageListMessageDTO {

    private int code;
    private String message;
    private long messageSendUserSeq;
    private List<MessageChatResDTO> messageChats;
}
