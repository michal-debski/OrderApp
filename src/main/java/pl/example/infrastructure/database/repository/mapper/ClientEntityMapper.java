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
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "carts", ignore = true)
    Client mapFromEntity(ClientEntity entity);

    ClientEntity mapToEntity(Client client);

}
