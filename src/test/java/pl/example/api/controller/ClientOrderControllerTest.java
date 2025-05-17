package pl.example.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.example.api.controller.exception.NotFoundException;
import pl.example.api.dto.OrderDTO;
import pl.example.api.dto.UserDTO;
import pl.example.api.dto.mapper.MealMapper;
import pl.example.api.dto.mapper.OrderMapper;
import pl.example.api.dto.mapper.RestaurantMapper;
import pl.example.business.*;
import pl.example.domain.Order;
import pl.example.domain.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static pl.example.util.DomainInput.kindOfClient;
import static pl.example.util.DomainInput.kindOfOrder;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = ClientOrderController.class)
class ClientOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;

    @MockBean
    private ClientService clientService;

    @MockBean
    private MealMenuService mealMenuService;

    @MockBean
    private OrderService orderService;

    @MockBean
    private OrderItemService orderItemService;

    @MockBean
    private OrderMapper orderMapper;

    @MockBean
    private RestaurantMapper restaurantMapper;

    @MockBean
    private MealMapper mealMapper;

    @InjectMocks
    private ClientOrderController clientOrderController;

    @BeforeEach
    public void setUp() {

        User client = kindOfClient();
        when(clientService.findLoggedClient()).thenReturn(client);
    }


    @Test
    void testShowForm() throws Exception {

        mockMvc.perform(get("/client/order"))
                .andExpect(status().isOk())
                .andExpect(view().name("client_restaurant_select"));
    }

    @Test
    void showRestaurantsByStreet() throws Exception {

        mockMvc.perform(post("/client/order/Sample%20Street/restaurants")
                        .param("street", "Sample Street"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("client_found_restaurant"));
    }

    @Test
    void showMenuForCreatedOrder() throws Exception {

        String restaurantId = "1";

        this.mockMvc.perform(MockMvcRequestBuilders.get("/client/order/{restaurantId}/menu", restaurantId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("client_found_restaurant_menu"));
    }

    @Test
    void placeOrder() throws Exception {

        String restaurantId = "1";
        List<String> selectedMeals = Arrays.asList("meal1", "meal2");
        List<Integer> quantity = List.of(2, 3);


        Order order = kindOfOrder();
        order.setOrderNumber("123456");
        order.setUser(kindOfClient());
        OrderDTO orderDTO = OrderDTO.builder()
                .orderNumber(order.getOrderNumber())
                .client(UserDTO.builder().build())
                .build();

        when(orderService.buildOrder(anyInt(), anyList(), anyList())).thenReturn(order);
        when(orderMapper.mapToDTO(any(Order.class))).thenReturn(orderDTO);

        mockMvc.perform(post("/client/order/{restaurantId}/menu", restaurantId)
                        .param("meals", "meal1", "meal2")
                        .param("quantities", "2", "3"))
                .andExpect(status().isOk())
                .andExpect(view().name("order_done"))
                .andExpect(model().attributeExists("orderDTO"))
                .andExpect(model().attributeExists("orderNumber"));
    }

    @Test
    void deleteOrder() throws Exception {

        Order order = Order.builder().build();
        order.setOrderId(1);
        when(orderService.findByOrderNumber("123456")).thenReturn(java.util.Optional.of(order));

        mockMvc.perform(MockMvcRequestBuilders.delete("/client/order/123456"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/client/orders"));
    }

}