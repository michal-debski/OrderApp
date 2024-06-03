package pl.example.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.Model;
import pl.example.api.dto.MealDTO;
import pl.example.api.dto.RestaurantDTO;
import pl.example.api.dto.RestaurantOwnerDTO;
import pl.example.api.dto.mapper.MealMapper;
import pl.example.api.dto.mapper.RestaurantMapper;
import pl.example.api.dto.mapper.RestaurantOwnerMapper;
import pl.example.api.dto.mapper.RestaurantStreetMapper;
import pl.example.business.MealMenuService;
import pl.example.business.RestaurantOwnerService;
import pl.example.business.RestaurantService;
import pl.example.business.RestaurantStreetService;
import pl.example.domain.Meal;
import pl.example.domain.Restaurant;
import pl.example.domain.RestaurantOwner;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = RestaurantOwnerHomeController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class RestaurantOwnerHomeControllerTest {
    @MockBean
    private RestaurantService restaurantService;

    @MockBean
    private RestaurantStreetService restaurantStreetService;

    @MockBean
    private RestaurantStreetMapper restaurantStreetMapper;

    @MockBean
    private RestaurantOwnerService restaurantOwnerService;

    @MockBean
    private MealMenuService mealMenuService;

    @MockBean
    private RestaurantMapper restaurantMapper;

    @MockBean
    private MealMapper mealMapper;
    @MockBean
    private RestaurantOwnerMapper restaurantOwnerMapper;

    @MockBean
    private Model model;

    @InjectMocks
    private RestaurantOwnerHomeController restaurantOwnerHomeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void restaurantOwnerHomePage() {
        String viewName = restaurantOwnerHomeController.restaurantOwnerHomePage();
        assertEquals("restaurantOwner_homepage", viewName);
    }

    @Test
    void showRestaurants() {
        // Arrange
        RestaurantOwnerDTO loggedRestaurantOwnerDTO = new RestaurantOwnerDTO();
        loggedRestaurantOwnerDTO.setRestaurantOwnerId(1);
        RestaurantOwner loggedRestaurantOwner = RestaurantOwner.builder().build();

        when(restaurantOwnerService.findLoggedRestaurantOwner()).thenReturn(loggedRestaurantOwner);

        Restaurant restaurant = Restaurant.builder().restaurantId(1).build();
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setRestaurantId(1);
        List<Restaurant> restaurants = List.of(restaurant);
        List<RestaurantDTO> restaurantsDTO = List.of(restaurantDTO);
        when(restaurantService.findByRestaurantOwnerId(loggedRestaurantOwner.getRestaurantOwnerId()))
                .thenReturn(restaurants);
        when(restaurantMapper.map(restaurant)).thenReturn(restaurantDTO);

        Meal meal = Meal.builder().build();
        MealDTO mealDTO = new MealDTO();
        List<Meal> meals = List.of(meal);
        List<MealDTO> mealDTOList = List.of(mealDTO);
        when(mealMenuService.findAllBySelectedRestaurant(restaurant.getRestaurantId()))
                .thenReturn(meals);
        when(mealMapper.map(meal)).thenReturn(mealDTO);

        // Act
        String viewName = restaurantOwnerHomeController.showRestaurants(model);

        // Assert
        assertEquals("restaurantOwner_restaurants", viewName);
        verify(model).addAttribute("restaurantsDTO", restaurantsDTO);
        verify(model).addAttribute("mealsMap", Map.of(1, mealDTOList));
    }
}