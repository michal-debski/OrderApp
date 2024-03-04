package pl.example.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.OrderItem;
import pl.example.infrastructure.database.entity.OrderItemEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderItemMapperEntity {

    // @Mapping(target = "order.orderItems", ignore = true)
    OrderItemEntity mapToEntity(OrderItem orderItem);

    @Mapping(target = "order", ignore = true)
    @Mapping(target = "meal", ignore = true)
    OrderItem mapFromEntity(OrderItemEntity orderItemEntity);


}
