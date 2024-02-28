package pl.example.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.example.domain.Meal;
import pl.example.domain.Order;
import pl.example.domain.RestaurantOwner;
import pl.example.domain.Street;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

   private Integer restaurantId;
    private String name;

    private RestaurantOwnerDTO restaurantOwner;

}
