package pl.example.api.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.example.business.RestaurantService;

@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class RestaurantRestController {


    private final RestaurantService restaurantService;


}
