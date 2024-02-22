package pl.example.domain;

import lombok.*;
import pl.example.infrastructure.database.entity.RestaurantEntity;

import java.util.Set;
@With
@Value
@Builder
@EqualsAndHashCode(of = "email")
@ToString(of = {"restaurantOwnerId", "name", "surname", "restaurants"})
public class RestaurantOwner {

     Integer restaurantOwnerId;
     String name;
     String surname;
     String email;

     Set<RestaurantEntity> restaurants;
}
