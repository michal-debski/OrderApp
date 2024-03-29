package pl.example.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StreetDTO {

    private Integer streetId;
    private String name;
    private RestaurantDTO restaurant;


}
