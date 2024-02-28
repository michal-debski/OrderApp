package pl.example.domain;

import lombok.*;

import java.util.List;
import java.util.Set;

@With
@Data
@Builder
@EqualsAndHashCode(of = "restaurantId")
@ToString(of = {"restaurantId", "name", "streets","meals"})
public class Restaurant {

     Integer restaurantId;
     String name;
     List<Street> streets;
     RestaurantOwner restaurantOwner;
     Set<Order> orders;
     List<Meal> meals;
}
