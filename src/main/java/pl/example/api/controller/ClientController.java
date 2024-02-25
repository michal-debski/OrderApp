package pl.example.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.example.api.dto.MealDTO;
import pl.example.api.dto.mapper.MealMapper;
import pl.example.api.dto.mapper.RestaurantMapper;
import pl.example.business.MealMenuService;
import pl.example.business.RestaurantService;
import pl.example.domain.Restaurant;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/client")
public class ClientController {


    private static final String RESTAURANT = "/restaurant";
    private static final String AVAILABLE_RESTAURANTS = "/restaurants";

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;
    private final MealMenuService mealMenuService;
    private final MealMapper mealMapper;


    @GetMapping(value = AVAILABLE_RESTAURANTS)
    public String showRestaurants(Model model) {
        var availableRestaurants = restaurantService.findAll().stream()
                .map(restaurantMapper::map)
                .toList();
        model.addAttribute("restaurants", availableRestaurants);
        return "restaurant_portal";
    }

    @GetMapping("/restaurants/{name}/menu")
    public String showMenu(@PathVariable("name") String name, Model model) {
        Restaurant restaurant = restaurantService.findByName(name);
        List<MealDTO> mealMenu = mealMenuService.findAllBySelectedRestaurant(restaurant.getName()).stream()
                .map(mealMapper::map)
                .collect(Collectors.toList());
        model.addAttribute("mealMenu", mealMenu);
        return "restaurant_menu";
    }

}

