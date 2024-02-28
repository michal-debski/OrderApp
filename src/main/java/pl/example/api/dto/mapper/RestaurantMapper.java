package pl.example.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.example.api.dto.RestaurantDTO;
import pl.example.domain.Restaurant;

@Mapper(componentModel = "spring", unmappedTargetPolicy =  ReportingPolicy.IGNORE)
public interface RestaurantMapper  {


    RestaurantDTO map(final Restaurant restaurant);
    @Mapping(target = "streets", ignore = true)
    @Mapping(target = "meals", ignore = true)
    Restaurant mapFromDto(RestaurantDTO restaurant);
}
