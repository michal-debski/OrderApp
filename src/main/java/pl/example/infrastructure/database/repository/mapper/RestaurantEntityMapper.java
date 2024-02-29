package pl.example.infrastructure.database.repository.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.Restaurant;
import pl.example.infrastructure.database.entity.RestaurantEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantEntityMapper {

    @Mapping(target = "restaurantOwner", ignore = true)
    @Mapping(target = "streets", ignore = true)
    @Mapping(target = "meals", ignore = true)
    Restaurant mapFromEntity(RestaurantEntity entity);


    @Mapping(target = "meals", ignore = true)
    RestaurantEntity mapToEntity(Restaurant restaurant);

}