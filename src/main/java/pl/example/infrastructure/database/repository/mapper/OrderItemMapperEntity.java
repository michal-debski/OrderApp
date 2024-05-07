package pl.example.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.OrderItem;
import pl.example.infrastructure.database.entity.OrderItemEntity;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderItemMapperEntity {


    @Mapping(target = "order", ignore = true)
    OrderItem mapFromEntity(OrderItemEntity orderItemEntity);
    OrderItemEntity mapToEntity(OrderItem orderItem);

    Set<OrderItemEntity> mapToEntity(Set <OrderItem> menuItemOrder);

}
