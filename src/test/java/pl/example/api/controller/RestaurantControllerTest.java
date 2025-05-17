package pl.example.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.example.api.dto.AddressDTO;
import pl.example.api.dto.RestaurantDTO;
import pl.example.api.dto.mapper.AddressMapper;
import pl.example.api.dto.mapper.RestaurantMapper;
import pl.example.business.RestaurantOwnerService;
import pl.example.business.RestaurantService;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = RestaurantController.class)
class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

    @MockBean
    private RestaurantOwnerService restaurantOwnerService;

    @MockBean
    private RestaurantMapper restaurantMapper;

    @MockBean
    private AddressMapper addressMapper;

    @Test
    void testAddRestaurantForm() throws Exception {

        RestaurantDTO restaurantDTO = RestaurantDTO.builder().build();
        AddressDTO addressDTO = AddressDTO.builder().build();

        mockMvc.perform(MockMvcRequestBuilders.get("/restaurantOwner/restaurants/newRestaurant")
                        .flashAttr("restaurantDTO", restaurantDTO)
                        .flashAttr("addressDTO", addressDTO))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("restaurant_new"))
                .andExpect(MockMvcResultMatchers.model().attribute("restaurant", restaurantDTO))
                .andExpect(MockMvcResultMatchers.model().attribute("address", addressDTO));
    }

    @Test
    void testMakeRestaurant() throws Exception {

        RestaurantDTO restaurantDTO = RestaurantDTO.builder().build();
        AddressDTO addressDTO = AddressDTO.builder().build();

        mockMvc.perform(MockMvcRequestBuilders.post("/restaurantOwner/restaurants/newRestaurant")
                        .flashAttr("restaurantDTO", restaurantDTO)
                        .flashAttr("addressDTO", addressDTO))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/restaurantOwner/restaurants"))
                .andExpect(MockMvcResultMatchers.model().attribute("restaurantName", restaurantDTO.restaurantName()));
    }

    @Test
    void testDeleteRestaurant() throws Exception {

        String restaurantId = "1";

        mockMvc.perform(MockMvcRequestBuilders.delete("/restaurantOwner/restaurants/{restaurantId}/deleteRestaurant", restaurantId)
                        .flashAttr("restaurantDTO", RestaurantDTO.builder().build()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/restaurantOwner/restaurants"));

        Mockito.verify(restaurantService, Mockito.times(1)).deleteRestaurant(Integer.valueOf(restaurantId));
    }
}