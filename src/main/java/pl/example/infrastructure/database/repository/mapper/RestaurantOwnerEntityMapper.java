package pl.example.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.RestaurantOwner;
import pl.example.infrastructure.database.entity.RestaurantOwnerEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantOwnerEntityMapper {
    @Mapping(target = "restaurant.restaurantOwner", ignore = true)
    RestaurantOwner mapFromEntity(RestaurantOwnerEntity restaurantOwnerEntity);

    @Mapping(target = "restaurant.restaurantOwner", ignore = true)
    RestaurantOwnerEntity mapToEntity(RestaurantOwner restaurantOwner);
}
