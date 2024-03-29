package pl.example.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantOwnerDTO {
    private Integer restaurantOwnerId;
    private String name;
    private String surname;
    private String email;
}
