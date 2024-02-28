package pl.example.api.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.example.api.dto.*;
import pl.example.api.dto.mapper.*;
import pl.example.business.*;
import pl.example.domain.*;
import pl.example.infrastructure.database.repository.MealRepository;
import pl.example.infrastructure.database.repository.RestaurantRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurantOwner")
public class RestaurantOwnerController {


    static final String ADDING_RESTAURANT = "/add";

    private final RestaurantService restaurantService;
    private final MealMenuService mealMenuService;
    private final StreetService streetService;
    private final RestaurantRepository restaurantRepository;
    private final MealRepository mealRepository;
    private final RestaurantMapper restaurantMapper;
    private final CategoryService categoryService;

    private final MealMapper mealMapper;
    private final StreetMapper streetMapper;
    private final CategoryMapper categoryMapper;

    private final RestaurantOwnerService restaurantOwnerService;
    private final RestaurantOwnerMapper restaurantOwnerMapper;

    @GetMapping("/{restaurantId}/addMeal")
    public String showAddMealForm(@PathVariable Integer restaurantId,
                                  @ModelAttribute MealDTO mealDTO,
                                  Model model) {
        List<Category> categories = categoryService.findAll();

        model.addAttribute("categories", categories);
        model.addAttribute("meal", mealDTO);
        model.addAttribute("restaurantId", restaurantId);
        return "meal_new";
    }

    @PostMapping("/{restaurantId}/addMeal")
    public String addMealToMenu(
            @PathVariable Integer restaurantId,
            @Valid @ModelAttribute("mealDTO") MealDTO mealDTO,
            ModelMap model
    ) {
        CategoryDTO categoryDTO = mealDTO.getCategory();
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        RestaurantDTO restaurantDTO = restaurantMapper.map(restaurant);

        MealDTO meal = MealDTO.builder()
                .name(mealDTO.getName())
                .description(mealDTO.getDescription())
                .price(mealDTO.getPrice())
                .category(categoryDTO)
                .restaurant(restaurantDTO)
                .build();
        Meal mealToEntity = mealMapper.map(meal);


        mealMenuService.makeMealForRestaurant(restaurantId, mealToEntity);
        model.addAttribute("name", mealDTO.getName());
        model.addAttribute("category", categoryDTO.getCategoryId());
        model.addAttribute("description", mealDTO.getDescription());
        model.addAttribute("price", mealDTO.getPrice());

        return "redirect:/restaurantOwner/{restaurantId}/addMeal";
    }

    @GetMapping("/{restaurantOwnerId}")
    public String restaurantList(
            @PathVariable(value = "restaurantOwnerId", required = false) Integer restaurantOwnerId,
            Model model) {
        var restaurants = restaurantService.findByRestaurantOwnerId(restaurantOwnerId).stream()
                .map(restaurantMapper::map).toList();
        var meals = mealMenuService.findAllBySelectedRestaurant(restaurantService.findByRestaurantOwnerId(
                restaurantOwnerId).listIterator().next().getRestaurantId()).stream().map(mealMapper::map).toList();
        var streets = streetService
                .findAllByRestaurantId(restaurantService.findByRestaurantOwnerId(
                        restaurantOwnerId).listIterator().next().getRestaurantId()).stream()
                .map(streetMapper::map).toList();

        model.addAttribute("restaurants", restaurants);
        model.addAttribute("meals", meals);
        model.addAttribute("streets", streets);

        return "restaurant";
    }

    @GetMapping("/{restaurantOwnerId}/addRestaurant")
    public String makeRestaurant(
            @PathVariable(value = "restaurantOwnerId", required = false) Integer restaurantOwnerId,
            @ModelAttribute RestaurantDTO restaurantDTO,
            Model model
    ) {

        model.addAttribute("restaurantOwnerId", restaurantOwnerId);
        model.addAttribute("restaurant", restaurantDTO);

        return "restaurant_new";
    }

    @PostMapping("/{restaurantOwnerId}/addRestaurant")
    public String addRestaurant(
            @PathVariable Integer restaurantOwnerId,
            @Valid @ModelAttribute("restaurantDTO") RestaurantDTO restaurantDTO,

            ModelMap model
    ) {


        RestaurantOwner restaurantOwner = restaurantOwnerService.findById(restaurantOwnerId);
        RestaurantOwnerDTO restaurantOwnerDTO = restaurantOwnerMapper.map(restaurantOwner);
        RestaurantDTO dto = RestaurantDTO.builder()
                .name(restaurantDTO.getName())
                .restaurantOwner(restaurantOwnerDTO)

                .build();

        Restaurant restaurant = restaurantMapper.mapFromDto(dto);
        restaurantService.saveRestaurant(restaurant);

        model.addAttribute("name", restaurantDTO.getName());
        model.addAttribute("restaurantOwnerId", restaurantOwner.getRestaurantOwnerId());


        return "restaurant";
    }

}