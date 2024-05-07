package pl.example.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.example.api.controller.exception.NotFoundException;
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

@Controller
@RequiredArgsConstructor
public class RestaurantOwnerMealController {
    private final MealMenuService mealMenuService;
    private final MealMapper mealMapper;
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final RestaurantService restaurantService;

    @GetMapping("/restaurantOwner/restaurants/{restaurantId}/meals")
    public String showMeals(
            @PathVariable("restaurantId") Integer restaurantId,
            Model model) {

        var meals = mealMenuService.findAllBySelectedRestaurant(restaurantId).stream()
                .map(mealMapper::map)
                .toList();

        model.addAttribute("meals", meals);
        return "restaurant_meals";
    }

    @GetMapping("/restaurantOwner/restaurants/{restaurantId}/addMeal")
    public String addMealForm(@PathVariable Integer restaurantId,
                              @ModelAttribute MealDTO mealDTO,
                              Model model) {
        List<CategoryDTO> categoryDTOs = categoryService.findAll().stream().map(categoryMapper::map).toList();

        model.addAttribute("categories", categoryDTOs);
        model.addAttribute("meal", mealDTO);
        return "meal_new";
    }


    @PostMapping("/restaurantOwner/restaurants/{restaurantId}/addMeal")
    public String addMealToMenu(
            @PathVariable Integer restaurantId,
            @Valid @ModelAttribute("mealDTO") MealDTO mealDTO,
            Model model
    ) {
        CategoryDTO categoryDTO = mealDTO.getCategory();
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);


        Meal mealToSave = mealMapper.map(mealDTO);
        mealMenuService.makeMealForRestaurant(mealToSave, restaurant);
        model.addAttribute("name", mealDTO.getName());
        model.addAttribute("category", mealDTO.getCategory());
        model.addAttribute("description", mealDTO.getDescription());
        model.addAttribute("price", mealDTO.getPrice());

        return "redirect:/restaurantOwner/restaurants";
    }

    @DeleteMapping("/restaurantOwner/restaurants/{restaurantId}/{mealId}/cancelMeal")
    public String deleteMealByRestaurant(
            @PathVariable String restaurantId,
            @PathVariable String mealId
    ) {

        mealMenuService.delete(Integer.valueOf(mealId));
        return String.format("redirect:/restaurantOwner/restaurants/{restaurantId}/meals", restaurantId);
    }

    @GetMapping("/restaurantOwner/restaurants/{restaurantId}/{mealId}/update")
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

    @PutMapping("/restaurantOwner/restaurants/{restaurantId}/{mealId}/update")
    public String updateMeal(
            @PathVariable Integer restaurantId,
            @PathVariable Integer mealId,
            @ModelAttribute("meal") MealDTO mealDTO
    ) {
        Meal meal = mealMenuService.findMealById(mealId)
                .orElseThrow(
                        () -> new NotFoundException("Meal with Id: [%s] does not exist in database".formatted(mealId))
                );
        String name = mealDTO.getName();
        String description = mealDTO.getDescription();
        BigDecimal price = mealDTO.getPrice();
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        mealMenuService.update(meal, name, description, price, restaurant);
        return String.format("redirect:/restaurantOwner/restaurants/{restaurantId}/meals", restaurantId);
    }
}
