package pl.example.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import pl.example.domain.OrderItem;

import java.math.BigDecimal;
import java.util.Set;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public record OrderDTO(Integer orderId, String orderNumber, BigDecimal totalPrice, String status, String orderDate,
                       ClientDTO client, RestaurantDTO restaurant, Set<OrderItem> orderItems) {


}
