package pl.example.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.RestaurantOwner;
import pl.example.infrastructure.database.entity.RestaurantOwnerEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantOwnerEntityMapper {


    RestaurantOwner mapFromEntity(RestaurantOwnerEntity restaurantOwnerEntity);


    RestaurantOwnerEntity mapToEntity(RestaurantOwner restaurantOwner);


}
