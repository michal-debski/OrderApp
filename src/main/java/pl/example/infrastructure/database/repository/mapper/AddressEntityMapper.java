package pl.example.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.Address;
import pl.example.infrastructure.database.entity.AddressEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface AddressEntityMapper {
    Address mapFromEntity(AddressEntity entity);
    AddressEntity mapToEntity(Address address);
}
