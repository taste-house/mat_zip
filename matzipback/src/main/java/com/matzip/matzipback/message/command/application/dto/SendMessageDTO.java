package com.matzip.matzipback.message.command.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SendMessageDTO {

    @NotNull(message = "쪽지를 보내는 사람은 필수입니다.")
    private Long messageSendUserSeq;
    @NotNull(message = "쪽지를 받는 사람은 필수입니다.")
    private Long messageReceiveUserSeq;
    @NotNull(message = "쪽지 내용은 필수입니다.")
    @NotBlank(message = "쪽지 내용이 빈 값일 수 없습니다.")
    private String message_content;
}
