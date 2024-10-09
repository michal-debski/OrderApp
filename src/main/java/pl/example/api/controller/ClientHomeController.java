package pl.example.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.example.api.dto.mapper.OrderMapper;
import pl.example.business.ClientService;
import pl.example.business.OrderService;

@Controller
@AllArgsConstructor
@RequestMapping("/client")
public class ClientHomeController {
    private final OrderService orderService;
    private final ClientService clientService;
    private final OrderMapper orderMapper;

    @GetMapping()
    public String clientHomePage() {
        return "client_homepage";
    }

    @GetMapping("/orders")
    public String showAllOrders(
            Model model
    ) {

        var ordersDTOs = orderService.findOrderByClientId(
                        clientService.findLoggedClient().getClientId()
                ).stream()
                .map(orderMapper::mapToDTO);

        model.addAttribute("ordersDTOs", ordersDTOs);
        return "client_orders";
    }


}

