package com.matzip.matzipback.restaurant.command.application.conroller;

import com.matzip.matzipback.restaurant.command.application.dto.RestaurantCreateRequest;
import com.matzip.matzipback.restaurant.command.application.service.RestaurantCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "Restaurant", description = "음식점")
public class RestaurantCommandController {

    private final RestaurantCommandService restaurantCommandService;

    @PostMapping("/restaurant")
    @Operation(summary = "음식점 등록", description = "음식점을 등록한다.")
    public ResponseEntity<Void> createRestaurant(
            @RequestBody @Valid RestaurantCreateRequest restaurantRequest) {

        Long restaurantSeq = restaurantCommandService.createRestaurant(restaurantRequest);

        return ResponseEntity
                .created(URI.create("/api/vi/restaurant/" + restaurantSeq))
                .build();
    }
}
