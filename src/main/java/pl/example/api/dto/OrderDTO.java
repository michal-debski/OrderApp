package pl.example.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.example.domain.Client;
import pl.example.domain.Restaurant;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Integer orderId;
    private String orderNumber;
    private BigDecimal totalPrice;
    private String status;
    private OffsetDateTime deliveryDate;
    private OffsetDateTime orderDate;
    private Client client;
    private Restaurant restaurant;
}
