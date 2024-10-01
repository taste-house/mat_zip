package com.matzip.matzipback.users.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MessageDetailResDTO {

    private long messageSendUserSeq;
    private long messageReceiveUserSeq;
    private String sendUserNickname;
    private String receiveUserNickname;
    private LocalDateTime messageDate;
    private String messageContent;
}