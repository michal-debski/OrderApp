package pl.example.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import pl.example.api.dto.mapper.MealMapper;
import pl.example.api.dto.mapper.RestaurantMapper;
import pl.example.api.dto.mapper.RestaurantStreetMapper;
import pl.example.api.dto.mapper.UserMapper;
import pl.example.business.MealMenuService;
import pl.example.business.RestaurantOwnerService;
import pl.example.business.RestaurantService;
import pl.example.business.RestaurantStreetService;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = RestaurantOwnerHomeController.class)
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
    private UserMapper restaurantOwnerMapper;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private RestaurantOwnerHomeController restaurantOwnerHomeController;

    @Test
    void restaurantOwnerHomePage() throws Exception {
        String viewName = restaurantOwnerHomeController.restaurantOwnerHomePage();

        mockMvc.perform(get("/restaurantOwner"))
                .andExpect(status().isOk())
                .andExpect(view().name(viewName));

    }

    @Test
    void showRestaurants() throws Exception {

        mockMvc.perform(get("/restaurantOwner/restaurants"))
                .andExpect(status().isOk());
//                .andExpect(view().name(viewName));

    }
//        Arrange
//        RestaurantOwnerDTO loggedRestaurantOwnerDTO = new RestaurantOwnerDTO();
//        loggedRestaurantOwnerDTO.setRestaurantOwnerId(1);
//        RestaurantOwner loggedRestaurantOwner = RestaurantOwner.builder().build();
//
//        when(restaurantOwnerService.findLoggedRestaurantOwner()).thenReturn(loggedRestaurantOwner);
//
//        Restaurant restaurant = Restaurant.builder().restaurantId(1).build();
//        RestaurantDTO restaurantDTO = new RestaurantDTO();
//        restaurantDTO.setRestaurantId(1);
//        List<Restaurant> restaurants = List.of(restaurant);
//        List<RestaurantDTO> restaurantsDTO = List.of(restaurantDTO);
//        when(restaurantService.findByRestaurantOwnerId(loggedRestaurantOwner.getRestaurantOwnerId()))
//                .thenReturn(restaurants);
//        when(restaurantMapper.map(restaurant)).thenReturn(restaurantDTO);
//
//        Meal meal = Meal.builder().build();
//        MealDTO mealDTO = new MealDTO();
//        List<Meal> meals = List.of(meal);
//        List<MealDTO> mealDTOList = List.of(mealDTO);
//        when(mealMenuService.findAllBySelectedRestaurant(restaurant.getRestaurantId()))
//                .thenReturn(meals);
//        when(mealMapper.map(meal)).thenReturn(mealDTO);
//
//        // Act
//        String viewName = restaurantOwnerHomeController.showRestaurants(model);
//
//        // Assert
//        assertEquals("restaurantOwner_restaurants", viewName);
//        verify(model).addAttribute("restaurantsDTO", restaurantsDTO);
//        verify(model).addAttribute("mealsMap", Map.of(1, mealDTOList));
//    }
}