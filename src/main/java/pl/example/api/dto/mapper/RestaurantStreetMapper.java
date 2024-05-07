package pl.example.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.example.api.dto.RestaurantStreetDTO;
import pl.example.domain.RestaurantStreet;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantStreetMapper {

    default RestaurantStreetDTO mapToDTO(RestaurantStreet dto) {
        return RestaurantStreetDTO.builder()
                .id(dto.getId())
                .restaurant(dto.getRestaurant())
                .street(dto.getStreet())
                .build();
    }

    default RestaurantStreet map(RestaurantStreetDTO dto) {
        return RestaurantStreet.builder()
                .restaurant(dto.getRestaurant())
                .street(dto.getStreet())
                .build();
    }
}
