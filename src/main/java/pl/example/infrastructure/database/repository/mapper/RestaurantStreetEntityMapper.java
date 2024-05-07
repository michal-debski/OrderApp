package pl.example.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.RestaurantStreet;
import pl.example.infrastructure.database.entity.RestaurantStreetEntity;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantStreetEntityMapper {

    RestaurantStreet mapFromEntity(RestaurantStreetEntity entity);

    RestaurantStreetEntity mapToEntity(RestaurantStreet entity);

    Set<RestaurantStreetEntity> mapToEntity(Set<RestaurantStreet> restaurantStreets);
}
