package pl.example.api.dto;

import lombok.Builder;

@Builder

public record RestaurantOwnerDTO(Integer restaurantOwnerId, String name, String surname, String email) {
}
