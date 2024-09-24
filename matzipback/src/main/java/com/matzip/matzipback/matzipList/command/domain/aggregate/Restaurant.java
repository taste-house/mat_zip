package com.matzip.matzipback.matzipList.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "restaurant")
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantSeq;
    private String restaurantTitle;
    private String restaurantAddress;
    private String restaurantPhone;
    private BigDecimal restaurantStar;
}
