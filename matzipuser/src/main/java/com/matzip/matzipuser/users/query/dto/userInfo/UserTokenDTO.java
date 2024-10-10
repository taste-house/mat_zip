package com.matzip.matzipuser.users.query.dto.userInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserTokenDTO {

    private Long userSeq;
    private String userPassword;
    private String userAuth;
}
