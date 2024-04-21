package pl.example.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.Street;
import pl.example.infrastructure.database.entity.StreetEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StreetEntityMapper {

    Street mapFromEntity(StreetEntity entity);

    StreetEntity mapToEntity(Street entity);
}
