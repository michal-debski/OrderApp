package pl.example.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode(of = "cartId")
@ToString(of = {"cartId", "totalQuantity","totalPrice", "orderItems"})
public class Cart {
     Integer cartId;
     Integer totalQuantity;
     BigDecimal totalPrice;
     Set<OrderItem> orderItems;

}

