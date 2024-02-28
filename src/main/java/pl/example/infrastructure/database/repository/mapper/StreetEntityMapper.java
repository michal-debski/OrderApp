package pl.example.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.Restaurant;
import pl.example.domain.Street;
import pl.example.infrastructure.database.entity.RestaurantEntity;
import pl.example.infrastructure.database.entity.StreetEntity;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StreetEntityMapper {
  //  @Mapping(target = "restaurant.streets", ignore = true)
    Street mapFromEntity(StreetEntity entity);




}
