package com.matzip.matzipback.users.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@NoArgsConstructor/*(access = AccessLevel.PROTECTED)*/
@SQLDelete(sql = "UPDATE user SET user_status = 'delete', user_delete_date = NOW() WHERE user_seq = ?")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userSeq;
    private String userEmail;
    private String userPassword;
    private String userName;
    private String userPhone;
    private String userNickname;
    private String userSocialYn;
    private String userSocialSite;
    private String socialToken;
    private String userAuth;
    private String penaltyYn;
    private String businessVerifiedYn;
    @Enumerated(value = EnumType.STRING)
    private UserStatus userStatus = UserStatus.active;
    @CreatedDate
    private LocalDateTime userRegDate;
    private LocalDateTime userDeleteDate;
    private String pwResetToken;
    private LocalDateTime pwTokenDueTime;



}
