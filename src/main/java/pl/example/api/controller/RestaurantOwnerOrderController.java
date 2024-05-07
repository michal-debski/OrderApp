package pl.example.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import pl.example.api.controller.exception.NotFoundException;
import pl.example.api.dto.OrderDTO;
import pl.example.api.dto.mapper.OrderMapper;
import pl.example.business.OrderService;
import pl.example.domain.Order;

@Controller
@RequiredArgsConstructor
public class RestaurantOwnerOrderController {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    @GetMapping("/restaurantOwner/orders")
    public String showOrdersForRestaurant(
            Model model
    ) {

        var ordersDTOs = orderService.findAll().stream()
                .map(orderMapper::mapToDTO);

        model.addAttribute("orders", ordersDTOs);
        return "restaurants_orders";
    }

    @GetMapping("/restaurantOwner/orders/{orderNumber}/updateOrder")
    public String updateMealForm(
            @PathVariable String orderNumber,
            Model model
    ) {

        Order orderByOrderNumber = orderService.findByOrderNumber(orderNumber)
                .orElseThrow(
                        () -> new NotFoundException("Order with order number [%s] does not exist".formatted(orderNumber)
                        ));
        OrderDTO orderDTO = orderMapper.mapToDTO(orderByOrderNumber);

        model.addAttribute("order", orderDTO);
        return "order_update";
    }

    @PutMapping("/restaurantOwner/orders/{orderNumber}/updateOrder")
    public String updateOrderStatus(
            @PathVariable String orderNumber,
            @ModelAttribute("order") OrderDTO orderDTO
    ) {
        Order orderByOrderNumber = orderService.findByOrderNumber(orderNumber)
                .orElseThrow(
                        () -> new NotFoundException("Order with order number [%s] does not exist".formatted(orderNumber)
                        ));
        orderByOrderNumber.setStatus(orderDTO.getStatus());
        orderService.update(orderByOrderNumber);
        return String.format("redirect:/restaurantOwner/orders", orderNumber);

    }


}
