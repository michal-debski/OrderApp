package pl.example.api.dto;

import lombok.Builder;


@Builder

public record OrderItemDTO(Integer orderItemId, Integer quantity, MealDTO meal, OrderDTO order) {


}
