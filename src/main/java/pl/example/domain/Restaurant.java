package pl.example.domain;

import lombok.*;

import java.util.Set;

@Builder
@With
@Data
@Setter
@Getter
@EqualsAndHashCode(of = "restaurantId")
@ToString(of = {"restaurantId", "restaurantName", "phone", "email"})
public class Restaurant {

    Integer restaurantId;
    String restaurantName;
    String phone;
    String email;
    Address address;
    User restaurantOwner;
    Set<RestaurantStreet> restaurantStreets;
}
