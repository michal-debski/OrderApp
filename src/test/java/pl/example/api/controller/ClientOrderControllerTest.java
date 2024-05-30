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
import pl.example.api.dto.ClientDTO;
import pl.example.api.dto.OrderDTO;
import pl.example.api.dto.mapper.MealMapper;
import pl.example.api.dto.mapper.OrderMapper;
import pl.example.api.dto.mapper.RestaurantMapper;
import pl.example.business.*;
import pl.example.domain.Client;
import pl.example.domain.Order;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static pl.example.util.DomainInput.kindOfClient;
import static pl.example.util.DomainInput.kindOfOrder;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = ClientOrderController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class ClientOrderControllerTest {
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
        Client client = kindOfClient();
        when(clientService.findLoggedClient()).thenReturn(client);
    }


    @Test
    void testShowForm() throws Exception {

        mockMvc.perform(get("/client/addOrder"))
                .andExpect(status().isOk())
                .andExpect(view().name("client_restaurant_select"));
    }

    @Test
    void showRestaurantsByStreet() throws Exception {

        mockMvc.perform(post("/client/addOrder/Sample%20Street/restaurants")
                        .param("street", "Sample Street"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("client_found_restaurant"));
    }

    @Test
    void showMenuForCreatedOrder() throws Exception {
        String restaurantId = "1";
        this.mockMvc.perform(MockMvcRequestBuilders.get("/client/addOrder/{restaurantId}/menu", restaurantId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("client_found_restaurant_menu"));
    }

    @Test
    void placeOrder() throws Exception {
        String restaurantId = "1";
        List<String> selectedMeals = Arrays.asList("meal1", "meal2");
        Integer quantity = 2;


        Order order = kindOfOrder();
        order.setOrderNumber("123456");
        order.setClient(kindOfClient());
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderNumber("123456");
        orderDTO.setClient(new ClientDTO());

        when(orderService.buildOrder(Integer.valueOf(restaurantId))).thenReturn(order);
        when(orderService.findByOrderNumber(order.getOrderNumber())).thenReturn(Optional.of(order));
        when(orderService.save(order)).thenReturn(order);
        when(orderMapper.mapToDTO(order)).thenReturn(orderDTO);


        mockMvc.perform(post("/client/addOrder/{restaurantId}/menu", restaurantId)
                        .param("meals", "meal1", "meal2")
                        .param("quantity", "2"))
                .andExpect(status().isOk())
                .andExpect(view().name("order_done"))
                .andExpect(model().attributeExists("orderDTO"))
                .andExpect(model().attributeExists("orderNumber"))
                .andExpect(model().attributeExists("clientDTO"));


        verify(orderService, times(1)).buildOrder(Integer.valueOf(restaurantId));
        verify(orderService, times(1)).findByOrderNumber(order.getOrderNumber());
        verify(orderItemService, times(1)).save(selectedMeals, quantity, order);
        verify(orderService, times(1)).save(order);
        verify(orderMapper, times(1)).mapToDTO(order);
    }


    @Test
    void deleteOrder() throws Exception {
        Order order = Order.builder().build();
        order.setOrderId(1);
        when(orderService.findByOrderNumber("123456")).thenReturn(java.util.Optional.of(order));

        mockMvc.perform(MockMvcRequestBuilders.delete("/client/order/123456/cancelOrder"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/client/orders"));

        verify(orderItemService, times(1)).deleteOrderItemsByOrderId(order.getOrderId());
        verify(orderService, times(1)).deleteOrder(order);
    }
}