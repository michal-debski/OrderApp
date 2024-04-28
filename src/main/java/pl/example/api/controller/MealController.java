package pl.example.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import pl.example.api.dto.CategoryDTO;
import pl.example.api.dto.MealDTO;
import pl.example.api.dto.mapper.CategoryMapper;
import pl.example.api.dto.mapper.MealMapper;
import pl.example.business.CategoryService;
import pl.example.business.MealMenuService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MealController {
    private final MealMenuService mealMenuService;
    private final MealMapper mealMapper;
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

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
