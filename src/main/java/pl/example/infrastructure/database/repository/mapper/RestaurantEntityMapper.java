package pl.example.infrastructure.database.repository.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.example.domain.Restaurant;
import pl.example.infrastructure.database.entity.RestaurantEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantEntityMapper {


    Restaurant mapFromEntity(RestaurantEntity entity);


    RestaurantEntity mapToEntity(Restaurant restaurant);

}