package pl.example.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.example.api.dto.MealDTO;
import pl.example.api.dto.RestaurantDTO;
import pl.example.api.dto.mapper.MealMapper;
import pl.example.api.dto.mapper.RestaurantMapper;
import pl.example.api.dto.mapper.RestaurantStreetMapper;
import pl.example.business.MealMenuService;
import pl.example.business.RestaurantOwnerService;
import pl.example.business.RestaurantService;
import pl.example.business.RestaurantStreetService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurantOwner")
public class RestaurantOwnerHomeController {
    private final RestaurantService restaurantService;
    private final RestaurantOwnerService restaurantOwnerService;

    private final MealMenuService mealMenuService;
    private final RestaurantStreetService restaurantStreetService;
    private final RestaurantStreetMapper restaurantStreetMapper;
    private final RestaurantMapper restaurantMapper;
    private final MealMapper mealMapper;

    @GetMapping
    public String restaurantOwnerHomePage() {
        return "restaurantOwner_homepage";

    }

    @GetMapping("/restaurants")
    public String showRestaurants(
            Model model
    ) {
        Map<Integer, List<MealDTO>> mealsMap = new HashMap<>();

        var restaurantsDTO = restaurantService.findByRestaurantOwnerId(
                        restaurantOwnerService.findLoggedRestaurantOwner().getRestaurantOwnerId()
                ).stream()
                .map(restaurantMapper::map).toList();
        for (RestaurantDTO restaurant : restaurantsDTO) {
            Integer restaurantId = restaurant.getRestaurantId();
            var meals = mealMenuService.findAllBySelectedRestaurant(restaurantId).stream()
                    .map(mealMapper::map)
                    .collect(Collectors.toList());
            mealsMap.put(restaurantId, meals);

        }
        model.addAttribute("restaurantsDTO", restaurantsDTO);
        model.addAttribute("mealsMap", mealsMap);


        return "restaurantOwner_restaurants";
    }
}
