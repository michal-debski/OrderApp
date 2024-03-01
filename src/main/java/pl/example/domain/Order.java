package pl.example.domain;

import lombok.*;

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
    Client client;
    Restaurant restaurant;
}
