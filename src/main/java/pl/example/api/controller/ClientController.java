package pl.example.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.example.api.dto.*;
import pl.example.api.dto.mapper.*;
import pl.example.business.*;
import pl.example.domain.Order;
import pl.example.domain.OrderItem;
import pl.example.domain.Restaurant;
import pl.example.infrastructure.database.repository.jpa.OrderItemJpaRepository;
import pl.example.infrastructure.database.repository.jpa.OrderJpaRepository;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor

public class ClientController {


    private final RestaurantService restaurantService;
    private final OrderService orderService;
    private final ClientService clientService;

    private final OrderMapper orderMapper;

    private final RestaurantMapper restaurantMapper;

    private final ClientMapper clientMapper;

    private final MealMenuService mealMenuService;

    private final MealMapper mealMapper;

    private final OrderItemMapper orderItemMapper;
    private final OrderItemService orderItemService;

    private final OrderJpaRepository orderJpaRepository;
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
    @Transactional
    public String showMenuForCreatedOrder(
            @PathVariable String restaurantId,
            Model model
    ) {
        var meals = mealMenuService.findAllBySelectedRestaurant(Integer.valueOf(restaurantId)).stream()
                .map(mealMapper::map)
                .toList();


        model.addAttribute("restaurantId", restaurantId);
        model.addAttribute("meals", meals);
        //model.addAttribute("order", completedOrder);
        // model.addAttribute("orderNumber", completedOrder.getOrderNumber());
        return "client_found_restaurant_menu";


    }

    @PostMapping("client/addOrder/{restaurantId}/menu")
    @Transactional
    public String placeOrder(
            @PathVariable String restaurantId,
            //    @ModelAttribute String orderNumber,
            @RequestParam("mealIds") List<String> selectedMeals,
            @RequestParam("quantity") Integer quantity,
            @ModelAttribute OrderItemsDTO orderItems,
            Model model
    ) {
        ClientDTO clientDTO = clientMapper.mapToDTO(clientService.findLoggedClient());

        Restaurant restaurant = restaurantService.findRestaurantById(Integer.valueOf(restaurantId));
        RestaurantDTO restaurantDTO = restaurantMapper.map(restaurant);

        String restaurantName = restaurant.getName();
        List<OrderItemDTO> orderItemsDTOList = orderItems.getOrderItems();
        Set<OrderItem> orderItemsForOrder = getMenuItemOrders(orderItemsDTOList);
        Order completedOrder = orderService.buildOrder(orderItemsForOrder, restaurantName);

        Order finalOrderWithCalculatedPrice = completedOrder.withTotalPrice(orderService.getTotalOrderPrice(completedOrder.getOrderId()));
        orderItemService.save(selectedMeals,
                quantity,
                finalOrderWithCalculatedPrice);

        //   orderNumber = completedOrder.getOrderNumber();


        model.addAttribute("restaurantId", restaurantId);

        //    model.addAttribute("orderNumber", orderNumber);
        return "order_done";
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


    private Set<OrderItem> getMenuItemOrders(List<OrderItemDTO> menuItemOrderDTOList) {
        if (Objects.nonNull(menuItemOrderDTOList)) {
            return menuItemOrderDTOList.stream()
                    .filter(a -> a.getQuantity() > 0)
                    .map(orderItemMapper::map)
                    .collect(Collectors.toSet());
        } else return Collections.emptySet();
    }


}

