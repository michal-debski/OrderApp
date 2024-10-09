package pl.example.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;



@Builder
@NoArgsConstructor
@AllArgsConstructor
public record MealDTO(Integer mealId, String name,
                      CategoryDTO category, String description,
                      BigDecimal price, RestaurantDTO restaurant) {


}
