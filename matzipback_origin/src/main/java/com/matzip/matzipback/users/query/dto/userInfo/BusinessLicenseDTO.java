package com.matzip.matzipback.users.query.dto.userInfo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BusinessLicenseDTO {

    private long businessSeq;
    private long userSeq;
    private String businessName;
    private String businessNumber;
    private String businessAddress;
    private LocalDateTime verificationDate;
    private int businessStatus;

}
