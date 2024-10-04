package com.matzip.matzipuser.restaurant.command.infrastructure.repository;

import com.matzip.matzipuser.restaurant.command.domain.aggregate.Restaurant;
import com.matzip.matzipuser.restaurant.command.domain.repository.RestaurantRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRestaurantRepository extends RestaurantRepository, JpaRepository<Restaurant, Long> {
}
