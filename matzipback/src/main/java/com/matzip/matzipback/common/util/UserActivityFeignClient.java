package com.matzip.matzipback.common.util;

import com.matzip.matzipback.common.util.dto.UpdateUserActivityPointDTO;
import com.matzip.matzipback.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "matzip-userActivity-service", url = "localhost:8000", configuration = FeignClientConfig.class)
public interface UserActivityFeignClient {

    @PutMapping("/user/api/v1/user-activity/point")
    void updateUserActivityPoint(@RequestBody UpdateUserActivityPointDTO updateUserActivityPointDTO);
}
