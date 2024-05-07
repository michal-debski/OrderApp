package pl.example.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
public class RestaurantStreet {

    Integer id;
    Street street;
    Restaurant restaurant;

}
