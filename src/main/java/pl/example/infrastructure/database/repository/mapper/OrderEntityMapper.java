package pl.example.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.Order;
import pl.example.infrastructure.database.entity.OrderEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderEntityMapper {


    OrderEntity mapToEntity(Order order);

    @Mapping(target = "client", ignore = true)
    @Mapping(target = "restaurant", ignore = true)
    @Mapping(target = "orderDetail", ignore = true)
    Order mapFromEntity(OrderEntity entity);
}
