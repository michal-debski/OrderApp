package pl.example.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import pl.example.domain.RestaurantStreet;

import java.util.Set;


@Builder

public record RestaurantDTO(Integer restaurantId,
                            String restaurantName,
                            @Pattern(regexp = "^[+]\\d{2}\\s\\d{3}\\s\\d{3}\\s\\d{3}$")
                            String phone,
                            @Email
                            String email,
                            String country,
                            String city,
                            String street,
                            String number,
                            Set<RestaurantStreet> restaurantStreets) {


}
