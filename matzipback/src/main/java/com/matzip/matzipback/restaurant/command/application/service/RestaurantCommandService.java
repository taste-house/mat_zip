package com.matzip.matzipback.restaurant.command.application.service;

import com.matzip.matzipback.exception.NotFoundException;
import com.matzip.matzipback.restaurant.command.application.dto.RestaurantCreateRequest;
import com.matzip.matzipback.restaurant.command.application.dto.RestaurantUpdateRequest;
import com.matzip.matzipback.restaurant.command.domain.aggregate.Restaurant;
import com.matzip.matzipback.restaurant.command.domain.repository.RestaurantRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RestaurantCommandService {

    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public Long createRestaurant(RestaurantCreateRequest restaurantRequest) {

        Restaurant restaurant = restaurantRepository.save(
                modelMapper.map(restaurantRequest, Restaurant.class));

        return restaurant.getRestaurantSeq();
    }

    @Transactional
    public void updateRestaurant(Long restaurantSeq, RestaurantUpdateRequest restaurantRequest) {

        Restaurant restaurant = restaurantRepository.findById(restaurantSeq)
                .orElseThrow(() -> new NotFoundException("찾을 수 없는 음식점(seq: " + restaurantSeq + ")"));

        restaurant.updateRestaurantDetails(
                restaurantRequest.getRestaurantTitle(),
                restaurantRequest.getRestaurantAddress(),
                restaurantRequest.getRestaurantPhone()
        );
    }

    @Transactional
    public void deleteRestaurant(Long restaurantSeq) {

        restaurantRepository.deleteById(restaurantSeq);
    }
}
