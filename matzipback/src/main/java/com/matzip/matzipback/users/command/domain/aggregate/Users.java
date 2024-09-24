package com.matzip.matzipback.users.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@NoArgsConstructor/*(access = AccessLevel.PROTECTED)*/
@SQLDelete(sql = "UPDATE users SET user_status = 'delete', user_delete_date = NOW() WHERE user_seq = ?")
@Getter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userSeq;
    private String userEmail;
    private String userPassword;
    private String userName;
    private String userPhone;
    private String userNickname;
    private String userSocialYn;
    private String userSocialSite;
    private String socialToken;
    private String userAuth;
    @Column(nullable = false)
    private String penaltyYn;
    private String businessVerifiedYn;
    @Enumerated(value = EnumType.STRING)
    private UserStatus userStatus = UserStatus.active;
    @CreatedDate
    private LocalDateTime userRegDate;
    private LocalDateTime userDeleteDate;
    private String pwResetToken;
    private LocalDateTime pwTokenDueTime;

    @PrePersist
    public void prePersist() {
        if (socialToken == null) socialToken = "N";
        if (userRegDate == null) userRegDate = LocalDateTime.now();
        if (penaltyYn == null) penaltyYn = "N";
        if (businessVerifiedYn == null) businessVerifiedYn = "N";
        if (userStatus == null) userStatus = UserStatus.active;
    }

    public void encryptPassword(String password) {
        this.userPassword = password;
    }
}
