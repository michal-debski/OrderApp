package pl.example.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.example.api.controller.exception.NotFoundException;
import pl.example.api.dto.OrderDTO;
import pl.example.api.dto.mapper.ClientMapper;
import pl.example.api.dto.mapper.MealMapper;
import pl.example.api.dto.mapper.OrderMapper;
import pl.example.api.dto.mapper.RestaurantMapper;
import pl.example.business.*;
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
        var restaurants = restaurantService.findAllByStreetName(street)
                .stream().map(restaurantMapper::map).toList();
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
        Order order = orderService.buildOrder(Integer.valueOf(restaurantId));
        OrderDTO orderDTO = orderMapper.mapToDTO(order);

        model.addAttribute("restaurantId", restaurantId);
        model.addAttribute("meals", meals);
        model.addAttribute("orderDTO", orderDTO);
        return "client_found_restaurant_menu";
    }

    @PostMapping("client/addOrder/{restaurantId}/menu")
    public String placeOrder(
            @PathVariable String restaurantId,
            @RequestParam("orderNumber") String orderNumber,
            @RequestParam("meals") List<String> selectedMeals,
            @RequestParam("quantity") Integer quantity,
            Model model
    ) {

        Order order = orderService.findByOrderNumber(orderNumber).orElseThrow();
        if (selectedMeals != null && quantity != 0) {
            orderItemService.save(selectedMeals, quantity, order);
            order.setTotalPrice(orderService.getTotalOrderPrice(order.getOrderId()));
            Order savedOrder = orderService.save(order);
            OrderDTO orderDTOToShow = orderMapper
                    .mapToDTO(savedOrder);

            model.addAttribute("orderDTO", orderDTOToShow);
            model.addAttribute("orderNumber", orderNumber);
            return "order_done";
        } else {
            orderService.deleteOrder(order);
            throw new NotFoundException("You didn't choose any meals to your order");
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
