package com.matzip.matzipback.users.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@NoArgsConstructor/*(access = AccessLevel.PROTECTED)*/
@EntityListeners(AuditingEntityListener.class)  //@CreatedDate 사용위해
@SQLDelete(sql = "UPDATE users SET user_status = 'inactive', user_delete_date = NOW() WHERE user_seq = ?")
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
    public void prePersist() {  // 전처리(디폴트값 설정)
//        if (userRegDate == null) userRegDate = LocalDateTime.now();
        if (userAuth == null) userAuth = "user"; // 권한
        if (penaltyYn == null) penaltyYn = "N"; // 패널티여부
        if (businessVerifiedYn == null) businessVerifiedYn = "N"; // 사업자인증여부
    }

    // 비밀번호 암호화
    public void encryptPassword(String password) {
        this.userPassword = password;
    }

    // 닉네임 변경
    public void updateNickname(String nickname) {
        if (nickname != null && !nickname.trim().isEmpty()) {
            this.userNickname = nickname;
        }
    }

    // 휴대폰 번호 변경
    public void updatePhone(String phoneNumber) {
        this.userPhone = phoneNumber;
    }

    // 사업자 인증 상태 변경
    public void updateBusinessStatus(boolean isVerified) {
        this.businessVerifiedYn = isVerified ? "Y" : "N";
    }

    public void updateUserStatus(UserStatus status) {
        this.userStatus = status;
    }
}
