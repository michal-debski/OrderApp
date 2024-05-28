package pl.example.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;


@Builder
@Data
@Getter
@Setter
@With
public class Order {
    Integer orderId;
    String orderNumber;
    BigDecimal totalPrice;
    String status;
    OffsetDateTime orderDate;
    Client client;
    Restaurant restaurant;

    Set<OrderItem> orderItems;
}
