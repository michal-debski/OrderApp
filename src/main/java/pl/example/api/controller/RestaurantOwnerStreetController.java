package pl.example.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.example.business.RestaurantService;
import pl.example.business.RestaurantStreetService;
import pl.example.business.StreetService;
import pl.example.domain.Restaurant;

import java.util.List;

@Controller
@AllArgsConstructor
public class RestaurantOwnerStreetController {
    private RestaurantStreetService restaurantStreetService;
    private RestaurantService restaurantService;

    private StreetService streetService;

    @GetMapping("/restaurantOwner/restaurants/{restaurantId}/streets")
    public String showStreets(
            @PathVariable("restaurantId") Integer restaurantId,
            @RequestParam(defaultValue = "0") int page,
            Model model) {
        int pageSize = 30;
        var streets = streetService.findAll(PageRequest.of(page, pageSize));

        model.addAttribute("streets", streets.getContent());
        model.addAttribute("currentPage, page");
        model.addAttribute("totalPages", streets.getTotalPages());
        return "restaurant_streets";
    }

    @PostMapping("/restaurantOwner/restaurants/{restaurantId}/streets")
    public String saveRestaurantStreets(
            @PathVariable Integer restaurantId,
            @RequestParam("streets") List<String> streets) {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        restaurantStreetService.save(restaurant, streets);

        return "redirect:/restaurantOwner/restaurants";
    }
}
