package pl.example.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.OrderItem;
import pl.example.infrastructure.database.entity.OrderItemEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderItemMapperEntity {

    OrderItemEntity mapToEntity(OrderItem orderItem);

    OrderItem mapFromEntity(OrderItemEntity orderItemEntity);


}
