package com.matzip.matzipback.users.command.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageReqDTO {

    private long messageSendUserSeq;
    private long messageReceiveUserSeq;
    private String messageContent;
    private String messageSenderDeletedYn;
    private String messageRecipientDeletedYn;
}
