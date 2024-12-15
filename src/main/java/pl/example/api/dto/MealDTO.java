package pl.example.api.dto;

import lombok.Builder;

import java.math.BigDecimal;


@Builder

public record MealDTO(Integer mealId, String name,
                      CategoryDTO category, String description,
                      BigDecimal price, RestaurantDTO restaurant) {


}
