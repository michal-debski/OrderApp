package pl.example.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.example.api.controller.exception.NotFoundException;
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
public class ClientOrderController {
    private final RestaurantService restaurantService;
    private final MealMenuService mealMenuService;
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final OrderItemService orderItemService;
    private final RestaurantMapper restaurantMapper;

    private final MealMapper mealMapper;

    @GetMapping("/client/addOrder")
    public String showForm() {
        return "client_restaurant_select";
    }

    @PostMapping("/client/addOrder/{street}/restaurants")
    public String showRestaurantsByStreet(
            @RequestParam String street,
            Model model
    ) {
        var restaurants = restaurantService.findAllByStreetName(street).stream()
                .map(restaurantMapper::map).toList();
        model.addAttribute("restaurants", restaurants);
        return "client_found_restaurant";

    }

    @GetMapping("/client/addOrder/{restaurantId}/menu")
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

    @PostMapping("/client/addOrder/{restaurantId}/menu")
    public String placeOrder(
            @PathVariable String restaurantId,
            @RequestParam("meals") List<String> selectedMeals,
            @RequestParam("quantity") Integer quantity,
            Model model
    ) {
        Order order = orderService.buildOrder(Integer.valueOf(restaurantId));
        Order orderByOrderNumber = orderService.findByOrderNumber(order.getOrderNumber()).orElseThrow();
        if (!selectedMeals.isEmpty()) {
            orderItemService.save(selectedMeals, quantity, orderByOrderNumber);
            orderByOrderNumber.setTotalPrice(orderService.getTotalOrderPrice(orderByOrderNumber.getOrderId()));
            Order savedOrder = orderService.save(orderByOrderNumber);
            OrderDTO orderDTOToShow = orderMapper
                    .mapToDTO(savedOrder);

            model.addAttribute("orderDTO", orderDTOToShow);
            model.addAttribute("orderNumber", orderDTOToShow.getOrderNumber());
            model.addAttribute("clientDTO", orderDTOToShow.getClient());
            return "order_done";
        } else {
            orderService.deleteOrder(orderByOrderNumber);
            throw new NotFoundException("You didn't choose any meals to your orderByOrderNumber");
        }


    }

    @DeleteMapping("/client/order/{orderNumber}/cancelOrder")
    public String deleteOrder(
            @PathVariable String orderNumber
    ) {
        Order order = orderService.findByOrderNumber(orderNumber).orElseThrow();

        orderItemService.deleteOrderItemsByOrderId(order.getOrderId());
        orderService.deleteOrder(order);

        return "redirect:/client/orders";
    }
}
