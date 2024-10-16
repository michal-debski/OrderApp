package pl.example.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.example.domain.Restaurant;
import pl.example.domain.Street;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public record RestaurantStreetDTO ( Integer id, Street street, Restaurant restaurant){


}
