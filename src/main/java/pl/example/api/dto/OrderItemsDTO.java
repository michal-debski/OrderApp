package pl.example.api.dto;

import lombok.Builder;

import java.util.List;


@Builder
public record OrderItemsDTO(List<OrderItemDTO> orderItems) {
}
