package com.matzip.matzipback.report.command.infrastructure.service;

import com.matzip.matzipback.config.FeignClientConfig;
import com.matzip.matzipback.report.command.dto.UpdateUserStatusDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "matzip-user-service", url = "localhost:8000", configuration = FeignClientConfig.class)
public interface UserInfraService {


    // 회원 상태 변경
    @PutMapping("/userStatus")
    @Operation(summary = "회원 상태 변경", description = "회원의 상태를 변경한다.")
    void updateUserStatus(@RequestBody UpdateUserStatusDTO updateUserStatusDTO);
}
