package pl.example.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
@Data

public class OrderItem {
    Integer orderItemId;
    Integer quantity;
    Meal meal;
    Order order;

}
