package com.matzip.matzipuser.users.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "message_block")
@NoArgsConstructor/*(access = AccessLevel.PROTECTED)*/
public class MessageBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageBlockSeq; // 차단고유번호

    @Column(name = "message_block_request_user_seq")
    private Long requestUserSeq; // 차단 요청자

    @Column(name = "message_blocked_user_seq")
    private Long blockedUserSeq; // 차단 대상자

}
