package pl.example.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "restaurantOwnerId")
@ToString(of = {"restaurantOwnerId", "name", "surname", "phone", "email"})
public class RestaurantOwner {

    Integer restaurantOwnerId;
    String name;
    String surname;
    String email;
    String phone;

}
