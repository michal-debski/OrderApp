package pl.example.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.example.api.controller.exception.MealNotFoundException;
import pl.example.api.dto.CategoryDTO;
import pl.example.api.dto.MealDTO;
import pl.example.api.dto.mapper.CategoryMapper;
import pl.example.api.dto.mapper.MealMapper;
import pl.example.business.CategoryService;
import pl.example.business.MealMenuService;
import pl.example.business.RestaurantService;
import pl.example.domain.Meal;
import pl.example.domain.Restaurant;

import java.math.BigDecimal;
import java.util.List;

import static java.lang.String.format;

@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurantOwner/restaurants")
public class RestaurantOwnerMealController {
    private final MealMenuService mealMenuService;
    private final MealMapper mealMapper;
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final RestaurantService restaurantService;

    @GetMapping("/{restaurantId}/meals")
    public String showMeals(
            @PathVariable("restaurantId") Integer restaurantId,
            Model model) {

        var meals = mealMenuService.findAllBySelectedRestaurant(restaurantId).stream()
                .map(mealMapper::map)
                .toList();

        model.addAttribute("meals", meals);
        return "restaurant_meals";
    }

    @GetMapping("/{restaurantId}/addMeal")
    public String addMealForm(@PathVariable Integer restaurantId,
                              @ModelAttribute MealDTO mealDTO,
                              Model model) {
        List<CategoryDTO> categoryDTOs = categoryService.findAllCategories().stream().map(categoryMapper::map).toList();

        model.addAttribute("categories", categoryDTOs);
        model.addAttribute("meal", mealDTO);
        return "meal_new";
    }


    @PostMapping("/{restaurantId}/addMeal")
    public String addMealToMenu(
            @PathVariable Integer restaurantId,
            @Valid @ModelAttribute("mealDTO") MealDTO mealDTO,
            Model model
    ) {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);


        Meal mealToSave = mealMapper.map(mealDTO);
        mealMenuService.makeMealForRestaurant(mealToSave, restaurant);
        model.addAttribute("name", mealDTO.name());
        model.addAttribute("category", mealDTO.category());
        model.addAttribute("description", mealDTO.description());
        model.addAttribute("price", mealDTO.price());

        return "redirect:/restaurantOwner/restaurants";
    }

    @DeleteMapping("/{restaurantId}/{mealId}/cancelMeal")
    public String deleteMealByRestaurant(
            @PathVariable String restaurantId,
            @PathVariable String mealId
    ) {
        mealMenuService.deleteMeal(Integer.valueOf(mealId));
        return format("redirect:/restaurantOwner/restaurants/{restaurantId}/meals", restaurantId);
    }

    @GetMapping("/{restaurantId}/{mealId}/update")
    public String updateMealForm(
            @PathVariable Integer restaurantId,
            @PathVariable Integer mealId,
            Model model
    ) {

        Meal meal = mealMenuService.findMealById(mealId)
                .orElseThrow(() -> new RuntimeException("Not found meal with id: [%s]".formatted(mealId)));
        MealDTO mealDTO = mealMapper.map(meal);

        model.addAttribute("meal", mealDTO);
        return "meal_update";
    }

    @PutMapping("/{restaurantId}/{mealId}/update")
    public String updateMeal(
            @PathVariable Integer restaurantId,
            @PathVariable Integer mealId,
            @ModelAttribute("meal") MealDTO mealDTO
    ) {
        Meal meal = mealMenuService.findMealById(mealId)
                .orElseThrow(
                        () -> new MealNotFoundException("Meal with Id: [%s] does not exist in database".formatted(mealId))
                );
        String name = mealDTO.name();
        String description = mealDTO.description();
        BigDecimal price = mealDTO.price();
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        mealMenuService.updateMeal(meal, name, description, price, restaurant);
        return format("redirect:/restaurantOwner/restaurants/{restaurantId}/meals", restaurantId);
    }
}
