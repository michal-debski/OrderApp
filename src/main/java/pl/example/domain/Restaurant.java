package pl.example.domain;

import lombok.*;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "restaurantId")
@ToString(of = {"restaurantId", "restaurantName", "phone", "email"})
public class Restaurant {

     Integer restaurantId;
     String restaurantName;
     String phone;
     String email;
     Address address;
     RestaurantOwner restaurantOwner;
     Set<RestaurantStreet> restaurantStreets;
}
