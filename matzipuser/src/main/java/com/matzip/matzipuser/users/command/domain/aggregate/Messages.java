package com.matzip.matzipuser.users.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@NoArgsConstructor/*(access = AccessLevel.PROTECTED)*/
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long messageSeq;

    @Column(name = "message_send_user_seq")
    private Long sendUserSeq; // 발신회원고유번호

    @Column(name = "message_receive_user_seq")
    private Long receiveUserSeq; // 수신회원고유번호

    private LocalDateTime messageDate; // 발송일
    private String messageContent; // 쪽지내용
    private String recipientReadYn; // 수신회원읽음여부
    private String senderDeletedYn; // 발신회원삭제여부
    private String recipientDeletedYn; // 수신회원삭제여부

}
