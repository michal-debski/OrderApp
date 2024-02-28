package pl.example.domain;

import lombok.*;
import pl.example.infrastructure.database.entity.MealEntity;
import pl.example.infrastructure.database.entity.OrderEntity;

import java.math.BigDecimal;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "orderDetailId")
@ToString(of = {"orderDetailId", "totalQuantity", "totalPrice", "meal", "orders"})
public class OrderDetailEntity {

    Integer orderDetailId;


    Integer totalQuantity;


    BigDecimal totalPrice;


    MealEntity meal;


    Set<OrderEntity> orders;


}
