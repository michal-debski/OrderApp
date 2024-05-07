package pl.example.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.example.domain.OrderItem;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Integer orderId;
    private String orderNumber;
    private BigDecimal totalPrice;
    private String status;
    private String orderDate;
    private ClientDTO client;
    private RestaurantDTO restaurant;
    private Set<OrderItem> orderItems;


}
