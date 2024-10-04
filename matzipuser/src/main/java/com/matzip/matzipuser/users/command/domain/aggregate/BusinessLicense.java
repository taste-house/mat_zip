package com.matzip.matzipuser.users.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "buisiness_license")
@NoArgsConstructor/*(access = AccessLevel.PROTECTED)*/
public class BusinessLicense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long businessSeq;

    /*@OneToOne
    @JoinColumn(name = "user_seq", referencedColumnName = "userSeq", unique = true)
    private Users user;*/

    private Long userSeq;
    private String businessNumber;
    private String businessName;
    private String businessAddress;
    @CreatedDate
    private LocalDateTime businessVerificationDate;
    private boolean businessVerificationStatus;

}
