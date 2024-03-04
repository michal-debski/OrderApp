package pl.example.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private Integer orderItemId;
    private Integer quantity;
    private MealDTO meal;
    private OrderDTO order;

}
