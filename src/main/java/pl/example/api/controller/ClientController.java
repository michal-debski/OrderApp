package pl.example.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.example.api.dto.ClientDTO;
import pl.example.api.dto.OrderDTO;
import pl.example.api.dto.OrderItemDTO;
import pl.example.api.dto.RestaurantDTO;
import pl.example.api.dto.mapper.*;
import pl.example.business.*;
import pl.example.domain.*;
import pl.example.infrastructure.database.repository.jpa.OrderItemJpaRepository;
import pl.example.infrastructure.database.repository.jpa.OrderJpaRepository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@AllArgsConstructor
@RequestMapping("/client")
public class ClientController {


    private RestaurantService restaurantService;
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


    @GetMapping("/{clientId}")
    public ModelAndView clientHomePage(
            @PathVariable(value = "clientId") String clientId
    ) {
        Map<String, ?> model = preparedInputData(Integer.valueOf(clientId));

        return new ModelAndView("client_homepage", model);

    }

    private Map<String, ?> preparedInputData(Integer id) {

        var orders = orderService.findByClientId(id).stream().map(orderMapper::mapToDTO)
                .toList();
        return Map.of("ordersDTOs", orders, "clientId", id);
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

    @GetMapping("/{clientId}/addOrder/{restaurantId}/menu/{orderNumber}")
    @Transactional
    public String showMenu(
            @PathVariable String clientId,
            @PathVariable String restaurantId,
            @PathVariable String orderNumber,
            Model model
    ) {

        var meals = mealMenuService.findAllBySelectedRestaurant(Integer.valueOf(restaurantId)).stream()
                .map(mealMapper::map)
                .toList();
        Restaurant restaurant = restaurantService.findRestaurantById(Integer.valueOf(restaurantId));
        RestaurantDTO restaurantDTO = restaurantMapper.map(restaurant);
        Client client = clientService.findById(Integer.valueOf(clientId));
        ClientDTO clientDTO = clientMapper.mapToDTO(client);
        OrderDTO orderDTO = OrderDTO.builder()
                .orderNumber(generateOrderNumber())
                .client(clientDTO)
                .status("In progress")
                .totalPrice(BigDecimal.ZERO)
                .orderDate(OffsetDateTime.now())
                .restaurant(restaurantDTO)
                .build();

        Order order = orderMapper.map(orderDTO);
        orderService.save(order);
        model.addAttribute("restaurantId", restaurantId);
        model.addAttribute("clientId", clientId);
        model.addAttribute("meals", meals);
        model.addAttribute("order", order);
        model.addAttribute("orderNumber", order.getOrderNumber());
        return "client_found_restaurant_menu";
    }

    @PostMapping("/{clientId}/addOrder/{restaurantId}/menu/{orderNumber}")
    @Transactional
    public String placeOrder(
            @PathVariable String clientId,
            @PathVariable String restaurantId,
            @PathVariable(required = false) String orderNumber,
            @ModelAttribute("orderDTO") OrderDTO orderDTO,
            @RequestParam("mealIds") List<String> selectedMeals,
            @RequestParam("quantity") Integer quantity,
            ModelMap model
    ) {
        Client client = clientService.findById(Integer.valueOf(clientId));
        ClientDTO clientDTO = clientMapper.mapToDTO(client);
        orderNumber = orderDTO.getOrderNumber();
        Restaurant restaurant = restaurantService.findRestaurantById(Integer.valueOf(restaurantId));
        RestaurantDTO restaurantDTO = restaurantMapper.map(restaurant);

//        orderNumber = orderDTO.getOrderNumber();
        Order order = orderService.findByOrderNumber(orderNumber).orElseThrow();
        OrderDTO dto = orderMapper.mapToDTO(order);

        List<Integer> mealIds = selectedMeals.stream()
                .map(Integer::valueOf)
                .toList();
        List<OrderItemDTO> orderItems = new ArrayList<>();
        for (Integer meal : mealIds) {

            Meal mealTemp = mealMenuService.findMealById(meal).orElseThrow();

            OrderItemDTO orderItemDTOFromClient = OrderItemDTO.builder()
                    .meal(mealMapper.map(mealTemp))
                    .quantity(quantity)
                    .order(dto)
                    .build();
            orderItems.add(orderItemDTOFromClient);
        }
        for (OrderItemDTO orderItem : orderItems) {
            OrderItem orderToSave = orderItemMapper.map(orderItem);
            orderItemService.save(orderToSave);

        }

        BigDecimal totalPrice = orderJpaRepository.getTotalOrderPrice(order.getOrderId());
        dto.setStatus("Preparing by chef");
        dto.setTotalPrice(totalPrice);
        dto.setClient(clientDTO);
        dto.setRestaurant(restaurantDTO);
        Order orderTemp = orderMapper.map(dto);
        orderService.save(orderTemp);


        model.addAttribute("clientId", clientId);
        model.addAttribute("restaurantId", restaurantId);
        model.addAttribute("clientName", clientService.findById(Integer.valueOf(clientId)).getName());
        model.addAttribute("orderNumber", orderNumber);
        return "order_done";
    }


    @GetMapping("/{clientId}/order/{orderNumber}/orderDetails")
    public String showOrderDetails(
            @PathVariable("clientId") String clientId,
            @PathVariable("orderNumber") String orderNumber,
            @ModelAttribute("orderDTO") OrderDTO orderDTO,
            Model model) {
        Client client = clientService.findById(Integer.valueOf(clientId));
        orderNumber = orderDTO.getOrderNumber();
        Order order = orderService.findByOrderNumber(orderNumber).orElseThrow();
        OrderDTO dto = orderMapper.mapToDTO(order);


        model.addAttribute("order", dto);


        return "order_details";
    }


    private String generateOrderNumber() {
        return "%s%s%s%s-%s%s".formatted(
                randomChar('A', 'Z'),
                randomChar('A', 'Z'),
                randomChar('A', 'Z'),
                randomChar('A', 'Z'),
                randomInt(10, 100),
                randomInt(10, 100)
        );
    }

    @SuppressWarnings("SameParameterValue")
    private int randomInt(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }

    @SuppressWarnings("SameParameterValue")
    private int randomChar(char min, char max) {
        return (char) new Random().nextInt(max - min) + min;
    }


}

