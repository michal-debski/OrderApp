package pl.example.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.example.api.dto.ClientDTO;
import pl.example.api.dto.OrderDTO;
import pl.example.api.dto.RestaurantDTO;
import pl.example.domain.Order;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface OrderMapper extends OffsetDateTimeMapper {
    @Mapping(source = "orderDate", target = "orderDate", qualifiedByName = "mapOffsetDateTimeToString")
    default OrderDTO mapToDTO(Order order) {
        return OrderDTO.builder()
                .orderNumber(order.getOrderNumber())
                .client(ClientDTO.builder()
                        .clientId(order.getClient().getClientId())
                        .name(order.getClient().getName())
                        .surname(order.getClient().getSurname())
                        .email(order.getClient().getEmail())
                        .phone(order.getClient().getPhone())
                        .build())
                .status(order.getStatus())
                .totalPrice(order.getTotalPrice())
                .orderDate(mapOffsetDateTimeToString(order.getOrderDate()))
                .restaurant(RestaurantDTO.builder()
                        .restaurantId(order.getRestaurant().getRestaurantId())
                        .restaurantName(order.getRestaurant().getRestaurantName())
                        .email(order.getRestaurant().getEmail())
                        .phone(order.getRestaurant().getPhone())
                        .country(order.getRestaurant().getAddress().getCountry())
                        .city(order.getRestaurant().getAddress().getCity())
                        .street(order.getRestaurant().getAddress().getStreet())
                        .number(order.getRestaurant().getAddress().getNumber())
                        .build())
                .orderItems(order.getOrderItems())
                .build();
    }

}
