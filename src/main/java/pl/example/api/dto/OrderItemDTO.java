package pl.example.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public record OrderItemDTO (  Integer orderItemId,  Integer quantity, MealDTO meal, OrderDTO order) {


}
