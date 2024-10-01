package com.matzip.matzipback.users.query.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// 대화방
@Getter
@Setter
@Builder
public class MessageChatResDTO {

    private long messageReceiveUserSeq; // 누구한테 보냈는지
    private String userNickname;
    private LocalDateTime recentMessageDate;
}
