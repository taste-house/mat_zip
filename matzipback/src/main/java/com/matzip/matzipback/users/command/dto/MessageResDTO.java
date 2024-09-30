package com.matzip.matzipback.users.command.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageResDTO {

    private long messageSeq;
    private long messageSendUserSeq;
    private long messageReceiveUserSeq;
    private LocalDateTime messageDate;
    private String messageContent;
    private String messageRecipientReadYn;
    private String messageSenderDeletedYn;
    private String messageRecipientDeletedYn;
}
