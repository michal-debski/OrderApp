package pl.example.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.example.api.dto.MealDTO;
import pl.example.api.dto.RestaurantDTO;
import pl.example.api.dto.StreetDTO;
import pl.example.api.dto.mapper.MealMapper;
import pl.example.api.dto.mapper.RestaurantMapper;
import pl.example.api.dto.mapper.StreetMapper;
import pl.example.business.MealMenuService;
import pl.example.business.RestaurantOwnerService;
import pl.example.business.RestaurantService;
import pl.example.business.StreetService;

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
    private final StreetService streetService;
    private final StreetMapper streetMapper;
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
        Map<Integer, List<StreetDTO>> streetsMap = new HashMap<>();
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

            var streets = streetService.findAllByRestaurantId(restaurantId).stream()
                    .map(streetMapper::map)
                    .collect(Collectors.toList());
            streetsMap.put(restaurantId, streets);
        }
        model.addAttribute("restaurantsDTO", restaurantsDTO);
        model.addAttribute("mealsMap", mealsMap);
        model.addAttribute("streetsMap", streetsMap);

        return "restaurantOwner_restaurants";
    }

    @GetMapping("/{restaurantOwnerId}")
    public String restaurantList(
            @PathVariable(value = "restaurantOwnerId", required = false) String restaurantOwnerId,
            Model model) {
        var restaurants = restaurantService.findByRestaurantOwnerId(Integer.valueOf(restaurantOwnerId)).stream()
                .map(restaurantMapper::map)
                .collect(Collectors.toList());

        Map<Integer, List<StreetDTO>> streetsMap = new HashMap<>();

        for (RestaurantDTO restaurant : restaurants) {
            Integer restaurantId = restaurant.getRestaurantId();

            var streets = streetService.findAllByRestaurantId(restaurantId).stream()
                    .map(streetMapper::map)
                    .collect(Collectors.toList());
            streetsMap.put(restaurantId, streets);
        }

        model.addAttribute("restaurants", restaurants);
        model.addAttribute("streetsMap", streetsMap);

        return "restaurant";
    }

//    @GetMapping("/{restaurantOwnerId}/restaurant/{restaurantId}/addMeal")
//    public String addMealForm(@PathVariable Integer restaurantId,
//                              @PathVariable Integer restaurantOwnerId,
//                              @ModelAttribute MealDTO mealDTO,
//                              Model model) {
//        List<Category> categories = categoryService.findAll();
//
//        model.addAttribute("categories", categories);
//        model.addAttribute("meal", mealDTO);
//        model.addAttribute("restaurantId", restaurantId);
//        model.addAttribute("restaurantOwnerId", restaurantOwnerId);
//        return "meal_new";
//    }
//
//
//    @PostMapping("/{restaurantOwnerId}/restaurant/{restaurantId}/addMeal")
//    public String addMealToMenu(
//            @PathVariable Integer restaurantId,
//            @PathVariable Integer restaurantOwnerId,
//            @Valid @ModelAttribute("mealDTO") MealDTO mealDTO,
//            ModelMap model
//    ) {
//        CategoryDTO categoryDTO = mealDTO.getCategory();
//        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
//        RestaurantDTO restaurantDTO = restaurantMapper.map(restaurant);
//
//        MealDTO dto = MealDTO.builder()
//                .name(mealDTO.getName())
//                .description(mealDTO.getDescription())
//                .price(mealDTO.getPrice())
//                .category(categoryDTO)
//                .restaurant(restaurantDTO)
//                .build();
//        Meal mealToEntity = mealMapper.map(dto);
//
//        mealMenuService.makeMealForRestaurant(mealToEntity);
//        model.addAttribute("name", mealDTO.getName());
//        model.addAttribute("category", categoryDTO.getCategoryId());
//        model.addAttribute("description", mealDTO.getDescription());
//        model.addAttribute("price", mealDTO.getPrice());
//
//        return "redirect:/restaurantOwner/{restaurantOwnerId}";
//    }
}
