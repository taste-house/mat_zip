package com.matzip.matzipback.users.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "follow")
@NoArgsConstructor/*(access = AccessLevel.PROTECTED)*/
public class Follow {

    @Id
    private Long followingUserSeq; // 팔로우하는 사용자

    @Id
    private Long followedUserSeq; // 팔로우 당하는 사용자

}
