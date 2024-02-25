package pl.example.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.Client;
import pl.example.domain.Order;
import pl.example.infrastructure.database.entity.ClientEntity;
import pl.example.infrastructure.database.entity.OrderEntity;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientEntityMapper {
       @Mapping(source = "orders", target = "orders", qualifiedByName = "mapOrders")
    Client mapFromEntity(ClientEntity entity);
    @Named("mapOrders")
    @SuppressWarnings("unused")
    default Set<Order> mapOrders(Set<OrderEntity> orderEntities) {
        return orderEntities.stream().map(this::mapFromEntity).collect(Collectors.toSet());
    }

    ClientEntity mapToEntity(Client client);

    @Mapping(target = "client", ignore = true)
    Order mapFromEntity(OrderEntity entity);
}
