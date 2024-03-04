package pl.example.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "orderItemId")
@ToString(of = {"orderItemId", "quantity"})
public class OrderItem {
    Integer orderItemId;
    Integer quantity;
    Meal meal;
    Order order;
}
