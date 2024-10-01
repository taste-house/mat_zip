package com.matzip.matzipback.restaurant.command.domain.repository;

import com.matzip.matzipback.restaurant.command.domain.aggregate.Restaurant;

public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);
}
