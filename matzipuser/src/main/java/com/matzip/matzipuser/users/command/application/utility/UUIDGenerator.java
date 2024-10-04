package com.matzip.matzipuser.users.command.application.utility;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class UUIDGenerator {
    public String createRandomNickname() {
        log.info("========랜덤 닉네임 생성========");
        return "user-" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }
}
