package pl.example.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.example.api.dto.ClientDTO;
import pl.example.api.dto.OrderDTO;
import pl.example.api.dto.RestaurantDTO;
import pl.example.domain.Order;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface OrderMapper {
    default OrderDTO mapToDTO(Order order) {
        return OrderDTO.builder()
                .orderNumber(order.getOrderNumber())
                .client(ClientDTO.builder()
                        .name(order.getClient().getName())
                        .surname(order.getClient().getSurname())
                        .email(order.getClient().getEmail())
                        .phone(order.getClient().getPhone())
                        .build())
                .status("In progress")
                .totalPrice(BigDecimal.ZERO)
                .orderDate(OffsetDateTime.now())
                .restaurant(RestaurantDTO.builder()
                        .restaurantId(order.getRestaurant().getRestaurantId())
                        .name(order.getRestaurant().getName())
                        .build())
                .build();
    }

    Order map(OrderDTO orderDTO);
}
