package pl.example.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.example.api.dto.OrderDTO;
import pl.example.api.dto.mapper.MealMapper;
import pl.example.api.dto.mapper.OrderMapper;
import pl.example.api.dto.mapper.RestaurantMapper;
import pl.example.business.MealMenuService;
import pl.example.business.OrderService;
import pl.example.business.RestaurantService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private RestaurantService restaurantService;
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final RestaurantMapper restaurantMapper;

    private final MealMenuService mealMenuService;
    private final MealMapper mealMapper;
    @GetMapping("/{clientId}")
    public String ordersList(
            @PathVariable(value = "clientId", required = false) String clientId,
            Model model
    ) {
        var orders = orderService.findByClientId(Integer.valueOf(clientId)).stream()
                .map(orderMapper::mapToDTO)
                .toList();

        Map<Integer, BigDecimal> ordersMap = new HashMap<>();
        for (OrderDTO order : orders) {
            Integer id = order.getOrderId();

            ordersMap.put(id, order.getTotalPrice());

        }

        model.addAttribute("mealsMap", ordersMap);

        return "client_homepage";
    }

    @GetMapping("/{clientId}/addOrder")
    public String showForm(@PathVariable String clientId, Model model) {
        model.addAttribute("clientId", clientId);
        return "client_restaurant_select";
    }

    @PostMapping("/{clientId}/addOrder/restaurants")
    public String showRestaurantsByStreet(
            @PathVariable String clientId,
            @RequestParam String street,
            Model model
    ) {
        var restaurants = restaurantService.findAllByStreetName(street).stream().map(restaurantMapper::map);
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("clientId", clientId);
        model.addAttribute("street", street);
        return "client_found_restaurant";

    }

    @GetMapping("/{clientId}/addOrder/{restaurantId}/menu")
    public String showMenu(
            @PathVariable String clientId,
            @PathVariable String restaurantId,
            Model model
    ) {

        var meals = mealMenuService.findAllBySelectedRestaurant(Integer.valueOf(restaurantId)).stream()
                .map(mealMapper::map)
                .toList();
        model.addAttribute("restaurantId", restaurantId);
        model.addAttribute("clientId", clientId);
        model.addAttribute("meals", meals);
        return "client_found_restaurant_menu";


    }


}

