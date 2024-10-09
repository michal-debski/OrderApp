package pl.example.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public record RestaurantOwnerDTO (Integer restaurantOwnerId, String name, String surname, String email){
}
