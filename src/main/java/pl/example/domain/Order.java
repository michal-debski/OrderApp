package pl.example.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;


@Builder
@Data
@Getter
@Setter
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
