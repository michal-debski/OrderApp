package pl.example.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.example.api.dto.OrderDTO;
import pl.example.api.dto.mapper.MealMapper;
import pl.example.api.dto.mapper.OrderMapper;
import pl.example.api.dto.mapper.RestaurantMapper;
import pl.example.business.MealMenuService;
import pl.example.business.OrderItemService;
import pl.example.business.OrderService;
import pl.example.business.RestaurantService;
import pl.example.domain.Order;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/client")
public class ClientOrderController {
    private final RestaurantService restaurantService;
    private final MealMenuService mealMenuService;
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final OrderItemService orderItemService;
    private final RestaurantMapper restaurantMapper;

    private final MealMapper mealMapper;

    @GetMapping("/addOrder")
    public String showForm() {
        return "client_restaurant_select";
    }

    @PostMapping("/addOrder/{street}/restaurants")
    public String showRestaurantsByStreet(
            @RequestParam String street,
            Model model
    ) {
        var restaurants = restaurantService.findAllByStreetName(street).stream()
                .map(restaurantMapper::map).toList();
        model.addAttribute("restaurants", restaurants);
        return "client_found_restaurant";

    }

    @GetMapping("/addOrder/{restaurantId}/menu")
    public String showMenuForCreatedOrder(
            @PathVariable String restaurantId,
            Model model
    ) {

        var meals = mealMenuService.findAllBySelectedRestaurant(Integer.valueOf(restaurantId)).stream()
                .map(mealMapper::map)
                .toList();

        model.addAttribute("restaurantId", restaurantId);
        model.addAttribute("meals", meals);
        return "client_found_restaurant_menu";
    }

    @PostMapping("/addOrder/{restaurantId}/menu")
    public String placeOrder(
            @PathVariable String restaurantId,
            @RequestParam(value = "meals", required = false) List<String> selectedMeals,
            @RequestParam(value = "quantities", required = false) List<Integer> quantities,
            Model model
    ) {
        Order order = orderService.buildOrder(Integer.valueOf(restaurantId), selectedMeals, quantities);
        OrderDTO orderDTOToShow = orderMapper
                .mapToDTO(order);

        model.addAttribute("orderDTO", orderDTOToShow);
        model.addAttribute("orderNumber", orderDTOToShow.orderNumber());
        model.addAttribute("clientDTO", orderDTOToShow.client());
        return "order_done";
    }


    @DeleteMapping("/order/{orderNumber}/cancelOrder")
    public String deleteOrder(
            @PathVariable String orderNumber
    ) {
        Order order = orderService.findByOrderNumber(orderNumber).orElseThrow();

        orderItemService.deleteOrderItemsByOrderId(order.getOrderId());
        orderService.deleteOrder(order);

        return "redirect:/client/orders";
    }
}
