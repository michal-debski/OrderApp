package pl.example.infrastructure.database.repository.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.Restaurant;
import pl.example.domain.RestaurantStreet;
import pl.example.infrastructure.database.entity.RestaurantEntity;
import pl.example.infrastructure.database.entity.RestaurantStreetEntity;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantEntityMapper {


    Restaurant mapFromEntity(RestaurantEntity entity);


    RestaurantEntity mapToEntity(Restaurant restaurant);
    Set<RestaurantEntity> mapToEntity(Set<Restaurant> restaurants);

    @Mapping(target = "restaurant", ignore = true)
    RestaurantStreet mapFromEntity(RestaurantStreetEntity entity);
    @Mapping(target = "restaurant", ignore = true)
    RestaurantStreetEntity mapToEntity(RestaurantStreet entity);

}