package pl.example.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.OrderItem;
import pl.example.infrastructure.database.entity.OrderItemEntity;
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderItemEntityMapper {

    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "meal", ignore = true)
    OrderItem mapFromEntity(OrderItemEntity entity);


    OrderItemEntity mapToEntity(OrderItem orderItem);
}
