package pl.example.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "restaurantId")
@ToString(of = {"restaurantId", "name"})
public class Restaurant {

     Integer restaurantId;
     String name;

     RestaurantOwner restaurantOwner;

}
