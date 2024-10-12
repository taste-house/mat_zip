package com.matzip.matzipback.message.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageSeq;
    private Long messageSendUserSeq;
    private Long messageReceiveUserSeq;

    @LastModifiedDate
    private LocalDateTime messageDate;
    private String messageContent;
    private String messageRecipientReadYn;
    private String messageSenderDeletedYn;
    private String messageRecipientDeletedYn;
}
