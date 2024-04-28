package pl.example.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.example.api.dto.OrderDTO;
import pl.example.api.dto.mapper.OrderMapper;
import pl.example.business.OrderService;
import pl.example.domain.Order;

@Controller
@AllArgsConstructor
public class ClientOrderDetailsController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping("/client/order/{orderNumber}/orderDetails")
    public String showOrderDetails(
            @PathVariable("orderNumber") String orderNumber,
            Model model) {

        Order order = orderService.findByOrderNumber(orderNumber).orElseThrow();
        OrderDTO dto = orderMapper.mapToDTO(order);
        model.addAttribute("order", dto);

        return "order_details";
    }
}
