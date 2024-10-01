package com.matzip.matzipback.restaurant.command.infrastructure.repository;

import com.matzip.matzipback.restaurant.command.domain.aggregate.Restaurant;
import com.matzip.matzipback.restaurant.command.domain.repository.RestaurantRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRestaurantRepository extends RestaurantRepository, JpaRepository<Restaurant, Long> {
}
