package pl.example.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.Client;
import pl.example.infrastructure.database.entity.ClientEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientEntityMapper {
   @Mapping(target = "orders", ignore = true)
    Client mapFromEntity(ClientEntity entity);
   @Mapping(target = "orders.client", ignore = true)
    ClientEntity mapToEntity(Client client);

}
