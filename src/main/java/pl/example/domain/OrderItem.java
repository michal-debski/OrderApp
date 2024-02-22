package pl.example.domain;

import lombok.*;

import java.math.BigDecimal;



@With
@Value
@Builder
@EqualsAndHashCode(of = "orderItemId")
@ToString(of = {"orderItemId", "quantity", "price", "cart", "meal"})
public class OrderItem {

     Integer orderItemId;


     Integer quantity;


     BigDecimal price;

     Cart cart;


     Meal meal;

}
