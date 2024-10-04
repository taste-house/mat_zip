package com.matzip.matzipuser.users.command.domain.aggregate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class FollowId implements Serializable {
    private Long followingUserSeq;
    private Long followedUserSeq;
}
