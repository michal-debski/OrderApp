package pl.example.api.dto;

import lombok.Builder;
import pl.example.domain.OrderItem;

import java.math.BigDecimal;
import java.util.Set;


@Builder

public record OrderDTO(Integer orderId, String orderNumber, BigDecimal totalPrice, String status, String orderDate,
                       ClientDTO client, RestaurantDTO restaurant, Set<OrderItem> orderItems) {


}
