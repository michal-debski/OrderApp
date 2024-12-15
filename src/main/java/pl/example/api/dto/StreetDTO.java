package pl.example.api.dto;

import lombok.Builder;


@Builder
public record StreetDTO(Integer streetId, String name, RestaurantDTO restaurant) {
}
