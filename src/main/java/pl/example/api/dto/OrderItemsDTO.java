package pl.example.api.dto;

import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public record OrderItemsDTO (List<OrderItemDTO> orderItems) { }
