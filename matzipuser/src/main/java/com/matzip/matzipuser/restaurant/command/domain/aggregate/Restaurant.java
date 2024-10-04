package com.matzip.matzipuser.restaurant.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "restaurant")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantSeq;
    private String restaurantTitle;
    private String restaurantAddress;
    private String restaurantPhone;
    private BigDecimal restaurantStar;

    public void updateRestaurantDetails(String restaurantTitle, String restaurantAddress, String restaurantPhone) {
        this.restaurantTitle = restaurantTitle;
        this.restaurantAddress = restaurantAddress;
        this.restaurantPhone = restaurantPhone;
    }

    public void updateRestaurantStar(BigDecimal restaurantStar) {
        this.restaurantStar = restaurantStar;
    }
}
