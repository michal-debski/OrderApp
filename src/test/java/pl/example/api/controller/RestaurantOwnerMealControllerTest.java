package pl.example.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import pl.example.api.dto.CategoryDTO;
import pl.example.api.dto.MealDTO;
import pl.example.api.dto.mapper.CategoryMapper;
import pl.example.api.dto.mapper.MealMapper;
import pl.example.business.CategoryService;
import pl.example.business.MealMenuService;
import pl.example.business.RestaurantService;
import pl.example.domain.Category;
import pl.example.domain.Meal;
import pl.example.domain.Restaurant;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static pl.example.util.DomainInput.kindOfMeal;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = RestaurantOwnerMealController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class RestaurantOwnerMealControllerTest {
    @MockBean
    private MealMenuService mealMenuService;

    @MockBean
    private MealMapper mealMapper;

    @MockBean
    private RestaurantService restaurantService;

    @MockBean
    private CategoryService categoryService;
    @MockBean
    private CategoryMapper categoryMapper;
    @MockBean
    private Model model;

    @InjectMocks
    private RestaurantOwnerMealController restaurantController;

    private MockMvc mockMvc;

    @Test
    void testShowMeals() {
        // Arrange
        Integer restaurantId = 1;
        Meal meal = kindOfMeal();
        MealDTO mealDTO = new MealDTO();
        List<Meal> meals = List.of(meal);
        List<MealDTO> mealDTOList = List.of(mealDTO);

        when(mealMenuService.findAllBySelectedRestaurant(restaurantId)).thenReturn(meals);
        when(mealMapper.map(meal)).thenReturn(mealDTO);

        // Act
        String viewName = restaurantController.showMeals(restaurantId, model);

        // Assert
        assertEquals("restaurant_meals", viewName);
        verify(model).addAttribute("meals", mealDTOList);
    }

    @Test
    void testAddMealForm() {
        // Arrange
        Integer restaurantId = 1;
        MealDTO mealDTO = new MealDTO();
        Category category = Category.builder().build();
        CategoryDTO categoryDTO = new CategoryDTO();
        List<Category> categories = List.of(category);
        List<CategoryDTO> categoryDTOList = List.of(categoryDTO);

        when(categoryService.findAll()).thenReturn(categories);
        when(categoryMapper.map(category)).thenReturn(categoryDTO);

        // Act
        String viewName = restaurantController.addMealForm(restaurantId, mealDTO, model);

        // Assert
        assertEquals("meal_new", viewName);
        verify(model).addAttribute("categories", categoryDTOList);
        verify(model).addAttribute("meal", mealDTO);
    }

    @Test
    void testAddMealToMenu() throws Exception {
        mockMvc.perform(post("/restaurantOwner/restaurants/1/addMeal")
                        .param("name", "Test Meal")
                        .param("description", "A delicious test meal")
                        .param("price", "9.99"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/restaurantOwner/restaurants"));

    }

    @Test
    void testDeleteMealByRestaurant() throws Exception {
        String restaurantId = "1";
        String mealId = "1";

        mockMvc.perform(
                        delete("/restaurantOwner/restaurants/{restaurantId}/{mealId}/cancelMeal",
                                restaurantId, mealId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/restaurantOwner/restaurants/" + restaurantId + "/meals"));

    }

    @Test
    void testUpdateMealForm() throws Exception {
        Meal mockMeal = Meal.builder().build();
        mockMeal.setMealId(1);


        MealDTO mockMealDTO = new MealDTO();

        when(mealMenuService.findMealById(1)).thenReturn(Optional.of(mockMeal));


        when(mealMapper.map(mockMeal)).thenReturn(mockMealDTO);


        mockMvc.perform(get("/restaurantOwner/restaurants/{restaurantId}/{mealId}/update", 1, 1))
                .andExpect(status().isOk())
                .andExpect(view().name("meal_update"))
                .andExpect(model().attributeExists("meal"))
                .andExpect(model().attribute("meal", mockMealDTO));
    }

    @Test
    void updateMeal() throws Exception {
        MealDTO mealDTO = new MealDTO();
        mealDTO.setName("Updated Meal Name");
        mealDTO.setDescription("Updated Meal Description");
        mealDTO.setPrice(BigDecimal.valueOf(20.0));

        Restaurant restaurant = Restaurant.builder().build();
        restaurant.setRestaurantId(1);

        Meal mockMeal = Meal.builder().build();
        when(mealMenuService.findMealById(1)).thenReturn(Optional.of(mockMeal));

        when(restaurantService.findRestaurantById(1)).thenReturn(restaurant);

        mockMvc.perform(put("/restaurantOwner/restaurants/{restaurantId}/{mealId}/update", 1, 1)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", mealDTO.getName())
                        .param("description", mealDTO.getDescription())
                        .param("price", mealDTO.getPrice().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/restaurantOwner/restaurants/1/meals"));

        verify(mealMenuService).update(mockMeal, mealDTO.getName(), mealDTO.getDescription(), mealDTO.getPrice(), restaurant);
    }
}