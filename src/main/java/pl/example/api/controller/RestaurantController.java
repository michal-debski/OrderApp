package pl.example.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.example.api.dto.AddressDTO;
import pl.example.api.dto.RestaurantDTO;
import pl.example.api.dto.mapper.AddressMapper;
import pl.example.api.dto.mapper.RestaurantMapper;
import pl.example.business.RestaurantOwnerService;
import pl.example.business.RestaurantService;
import pl.example.domain.Address;
import pl.example.domain.Restaurant;
import pl.example.domain.RestaurantOwner;

@Controller
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final RestaurantOwnerService restaurantOwnerService;
    private final RestaurantMapper restaurantMapper;
    private final AddressMapper addressMapper;

    @GetMapping("/restaurantOwner/restaurants/addRestaurant")
    public String addRestaurantForm(
            @ModelAttribute RestaurantDTO restaurantDTO,
            @ModelAttribute AddressDTO addressDTO,
            Model model
    ) {
        model.addAttribute("address", addressDTO);
        model.addAttribute("restaurant", restaurantDTO);
        return "restaurant_new";
    }

    @PostMapping("/restaurantOwner/restaurants/addRestaurant")
    public String makeRestaurant(
            @ModelAttribute("restaurantDTO") RestaurantDTO restaurantDTO,
            @ModelAttribute("addressDTO") AddressDTO addressDTO,
            Model model
    ) {
        RestaurantOwner loggedRestaurantOwner = restaurantOwnerService.findLoggedRestaurantOwner();
        Address address = addressMapper.map(addressDTO);
        Restaurant restaurant = restaurantMapper.mapFromDto(restaurantDTO);
        restaurantService.saveRestaurant(restaurant, loggedRestaurantOwner, address);

        model.addAttribute("restaurantName", restaurantDTO.getRestaurantName());
        return "redirect:/restaurantOwner/restaurants";
    }

    @DeleteMapping("/restaurantOwner/restaurants/{restaurantId}/deleteRestaurant")
    public String deleteMealByRestaurant(
            @PathVariable String restaurantId,
            @ModelAttribute RestaurantDTO restaurantDTO,
            Model model
    ) {

        restaurantService.deleteRestaurant(Integer.valueOf(restaurantId));
        model.addAttribute("restaurant", restaurantDTO);
        return "redirect:/restaurantOwner/restaurants";
    }
}
