package pl.example.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.example.api.dto.RestaurantDTO;
import pl.example.domain.Restaurant;

@Mapper(componentModel = "spring", unmappedTargetPolicy =  ReportingPolicy.IGNORE)
public interface RestaurantMapper  {

    @Mapping(target = "restaurantOwner", ignore = true)
    RestaurantDTO map(final Restaurant restaurant);


    @Mapping(target = "restaurant.orders", ignore = true)
    @Mapping(target = "restaurant.streets", ignore = true)
    Restaurant mapFromDto(RestaurantDTO restaurant);
}
