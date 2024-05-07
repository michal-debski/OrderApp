package pl.example.api.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.example.api.dto.RestaurantDTO;
import pl.example.domain.Address;
import pl.example.domain.Restaurant;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantMapper {


    default RestaurantDTO map(Restaurant restaurant) {
        return RestaurantDTO.builder()
                .restaurantId(restaurant.getRestaurantId())
                .restaurantName(restaurant.getRestaurantName())
                .phone(restaurant.getPhone())
                .email(restaurant.getEmail())
                .country(restaurant.getAddress().getCountry())
                .city(restaurant.getAddress().getCity())
                .street(restaurant.getAddress().getStreet())
                .number(restaurant.getAddress().getNumber())
                .restaurantStreets(restaurant.getRestaurantStreets())
                .build();
    }


    default Restaurant mapFromDto(RestaurantDTO restaurant) {
        return Restaurant.builder()
                .restaurantId(restaurant.getRestaurantId())
                .restaurantName(restaurant.getRestaurantName())
                .phone(restaurant.getPhone())
                .email(restaurant.getEmail())
                .address(Address.builder()
                        .country(restaurant.getCountry())
                        .city(restaurant.getCity())
                        .street(restaurant.getStreet())
                        .number(restaurant.getNumber()).build())
                .restaurantStreets(restaurant.getRestaurantStreets())
                .build();
    }
}
