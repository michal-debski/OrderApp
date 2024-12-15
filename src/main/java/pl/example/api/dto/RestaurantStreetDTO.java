package pl.example.api.dto;

import lombok.Builder;
import pl.example.domain.Restaurant;
import pl.example.domain.Street;


@Builder
public record RestaurantStreetDTO(Integer id, Street street, Restaurant restaurant) {


}
