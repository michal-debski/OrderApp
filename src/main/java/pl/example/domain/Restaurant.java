package pl.example.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "restaurantId")
@ToString(of = {"restaurantId", "name", "streets"})
public class Restaurant {

    Integer restaurantId;
    String name;
    Set<Street> streets;
    RestaurantOwner restaurantOwner;
    Set<Order> orders;
    Set<Meal> meals;
}
