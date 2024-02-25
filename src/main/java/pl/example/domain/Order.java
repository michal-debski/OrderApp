package pl.example.domain;

import lombok.*;
import pl.example.infrastructure.database.entity.ClientEntity;
import pl.example.infrastructure.database.entity.OrderDetailEntity;
import pl.example.infrastructure.database.entity.RestaurantEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "orderId")
@ToString(of = {"orderId", "orderNumber", "totalPrice", "status", "deliveryDate", "orderDate"})
public class Order {
    Integer orderId;
    String orderNumber;
    BigDecimal totalPrice;
    String status;
    OffsetDateTime deliveryDate;
    OffsetDateTime orderDate;
    ClientEntity client;
    RestaurantEntity restaurant;
    OrderDetailEntity orderDetail;
}
