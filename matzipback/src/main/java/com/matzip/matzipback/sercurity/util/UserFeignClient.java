package com.matzip.matzipback.sercurity.util;

import com.matzip.matzipback.config.FeignClientConfig;
import com.matzip.matzipback.responsemessage.SuccessSearchResMessage;
import com.matzip.matzipback.sercurity.dto.UserTokenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "matzip-user-token-service", url = "localhost:8000", configuration = FeignClientConfig.class)
public interface UserFeignClient {

    @GetMapping("/user/api/vi/users/email")
    SuccessSearchResMessage<UserTokenDTO> getUserByEmail(@RequestParam("email") String email);

    @GetMapping("/user/api/vi/users/userseq")
    SuccessSearchResMessage<UserTokenDTO> getUserByUserSeq(@RequestParam("userSeq") Long userSeq);
}
