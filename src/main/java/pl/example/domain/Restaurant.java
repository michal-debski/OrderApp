package pl.example.domain;

import lombok.*;
import pl.example.infrastructure.database.entity.MealEntity;
import pl.example.infrastructure.database.entity.StreetEntity;

import java.util.Set;
@With
@Value
@Builder
@EqualsAndHashCode(of = "restaurantId")
@ToString(of = {"restaurantId", "name", "streets", "restaurantOwner"})
public class Restaurant {

     Integer restaurantId;

     String name;

     Set<StreetEntity> streets;

     RestaurantOwner restaurantOwner;

     Set<MealEntity> meals;
}
