package pl.example.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.Meal;
import pl.example.domain.Order;
import pl.example.domain.OrderItem;
import pl.example.domain.RestaurantStreet;
import pl.example.infrastructure.database.entity.MealEntity;
import pl.example.infrastructure.database.entity.OrderEntity;
import pl.example.infrastructure.database.entity.OrderItemEntity;
import pl.example.infrastructure.database.entity.RestaurantStreetEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderEntityMapper {

    OrderEntity mapToEntity(Order order);


    Order mapFromEntity(OrderEntity entity);
    @Mapping(target = "order", ignore = true)
    OrderItem mapMenuItemOrderFromEntity(OrderItemEntity entity);

    @Mapping(target = "order", ignore = true)
    OrderItemEntity mapMenuItemOrderToEntity(OrderItem menuItemOrder);
    @Mapping(target = "restaurant", ignore = true)
    Meal mapFromEntity(MealEntity entity);
    @Mapping(target = "restaurant", ignore = true)
    RestaurantStreet mapFromEntity(RestaurantStreetEntity entity);
    @Mapping(target = "restaurant", ignore = true)
    RestaurantStreetEntity mapToEntity(RestaurantStreet entity);
}
