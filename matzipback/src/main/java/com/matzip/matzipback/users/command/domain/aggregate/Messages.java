package com.matzip.matzipback.users.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@EntityListeners(AuditingEntityListener.class)  //@CreatedDate 사용위해
@NoArgsConstructor/*(access = AccessLevel.PROTECTED)*/
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long messageSeq;

    @Column(name = "message_send_user_seq")
    private Long messageSendUserSeq; // 발신회원고유번호

    @Column(name = "message_receive_user_seq")
    private Long messageReceiveUserSeq; // 수신회원고유번호

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime messageDate; // 발송일
    private String messageContent; // 쪽지내용
    private String messageRecipientReadYn; // 수신회원읽음여부
    private String messageSenderDeletedYn; // 발신회원삭제여부
    private String messageRecipientDeletedYn; // 수신회원삭제여부

    @PrePersist
    public void prePersist() {
        if (messageRecipientReadYn == null) messageRecipientReadYn = "N";
        if (messageSenderDeletedYn == null) messageSenderDeletedYn = "N";
        if (messageRecipientDeletedYn == null) messageRecipientDeletedYn = "N";
    }
}
