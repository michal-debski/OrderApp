package pl.example.api.dto.mapper;

import org.mapstruct.Mapper;
import pl.example.api.dto.RestaurantDTO;
import pl.example.domain.Restaurant;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {



    RestaurantDTO map(Restaurant restaurant);
}
