package com.matzip.matzipback.message.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MessageShortDTO {

    private final Long messageReceiveUserSeq;
    private final LocalDateTime messageDate;
    private final String messageContent;
}
