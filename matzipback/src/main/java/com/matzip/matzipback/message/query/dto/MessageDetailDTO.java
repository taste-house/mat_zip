package com.matzip.matzipback.message.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MessageDetailDTO {

    private Long messageSeq;
    private Long messageSendUserSeq;
    private Long messageReceiveUserSeq;
    private LocalDateTime messageDate;
    private String messageContent;
    private String messageRecipientReadYn;
}
