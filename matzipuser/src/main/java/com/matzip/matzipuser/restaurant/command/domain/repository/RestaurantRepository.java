package com.matzip.matzipuser.restaurant.command.domain.repository;

import com.matzip.matzipuser.restaurant.command.domain.aggregate.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);

    Optional<Restaurant> findById(Long restaurantSeq);

    void deleteById(Long restaurantSeq);
}
