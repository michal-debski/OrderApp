package pl.example.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import pl.example.api.dto.OrderDTO;
import pl.example.api.dto.mapper.OrderMapper;
import pl.example.business.ClientService;
import pl.example.business.OrderService;
import pl.example.domain.Order;
import pl.example.infrastructure.database.repository.jpa.OrderItemJpaRepository;

import java.util.Map;

@Controller
@AllArgsConstructor

public class ClientController {
    private final OrderService orderService;
    private final ClientService clientService;

    private final OrderMapper orderMapper;

    private final OrderItemJpaRepository orderItemJpaRepository;


    @GetMapping
    public ModelAndView clientHomePage() {
        Integer clientId = clientService.findLoggedClient().getClientId();
        Map<String, ?> model = preparedInputData(clientId);

        return new ModelAndView("client_homepage", model);

    }

    private Map<String, ?> preparedInputData(Integer id) {

        var orders = orderService.findByClientId(id).stream().map(orderMapper::mapToDTO)
                .toList();
        return Map.of("ordersDTOs", orders);
    }

    @DeleteMapping("/{clientId}/order/{orderNumber}/cancelOrder")
    @Transactional
    public String deleteOrder(
            @PathVariable String clientId,
            @PathVariable String orderNumber,
            Model model
    ) {
        Order order = orderService.findByOrderNumber(orderNumber).orElseThrow();
        orderItemJpaRepository.deleteOrderItemsByOrderId(order.getOrderId());
        orderService.deleteOrder(order);
        model.addAttribute("clientName", clientService.findById(Integer.valueOf(clientId)).getName());
        model.addAttribute("clientId", clientId);

        return "order_cancel";
    }

    @GetMapping("/client/order/{orderNumber}/orderDetails")
    public String showOrderDetails(
            @PathVariable("orderNumber") String orderNumber,
            @ModelAttribute("orderDTO") OrderDTO orderDTO,
            Model model) {
        orderNumber = orderDTO.getOrderNumber();
        Order order = orderService.findByOrderNumber(orderNumber).orElseThrow();
        OrderDTO dto = orderMapper.mapToDTO(order);


        model.addAttribute("order", dto);


        return "order_details";
    }


}

