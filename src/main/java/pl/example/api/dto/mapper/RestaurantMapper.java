package pl.example.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.example.api.dto.RestaurantDTO;
import pl.example.domain.Restaurant;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {


    default RestaurantDTO map(Restaurant restaurant) {
        return RestaurantDTO.builder()
                .restaurantId(restaurant.getRestaurantId())
                .name(restaurant.getName())
                .build();
    }


    default Restaurant mapFromDto(RestaurantDTO restaurant) {
        return Restaurant.builder()
                .restaurantId(restaurant.getRestaurantId())
                .name(restaurant.getName())
                .build();
    }
}
