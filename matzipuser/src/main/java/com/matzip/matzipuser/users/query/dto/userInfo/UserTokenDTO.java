package com.matzip.matzipuser.users.query.dto.userInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTokenDTO {

    private Long userSeq;
    private String userPassword;
    private String userAuth;
}
