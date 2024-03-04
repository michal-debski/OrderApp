package pl.example.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;
import pl.example.api.dto.OrderDTO;
import pl.example.api.dto.mapper.*;
import pl.example.business.*;
import pl.example.domain.Client;
import pl.example.domain.Order;
import pl.example.infrastructure.database.repository.jpa.OrderItemJpaRepository;
import pl.example.infrastructure.database.repository.jpa.OrderJpaRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ClientController.class)
@AutoConfigureMockMvc(addFilters = false)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class ClientControllerTest {

    MockMvc mockMvc;

    @MockBean
    private final RestaurantService restaurantService;
    @MockBean
    private final OrderService orderService;
    @MockBean
    private final ClientService clientService;

    @MockBean
    private final OrderMapper orderMapper;

    @MockBean
    private final RestaurantMapper restaurantMapper;

    @MockBean
    private final ClientMapper clientMapper;

    @MockBean
    private final MealMenuService mealMenuService;

    @MockBean
    private final MealMapper mealMapper;

    @MockBean
    private final OrderItemMapper orderItemMapper;
    @MockBean
    private final OrderItemService orderItemService;

    @MockBean
    private final OrderJpaRepository orderJpaRepository;
    @MockBean
    private final OrderItemJpaRepository orderItemJpaRepository;

    @InjectMocks
    private ClientController clientController;

    @Test
    void deleteOrder() throws Exception {
        String clientId = "1";
        String orderNumber = "123";

        Order order = Order.builder().build();
        Order orderWithId = order.withOrderId(1);

        Client client = Client.builder().build();
        Client clientTest = client.withName("Testowy Klient");


        when(orderService.findByOrderNumber(orderNumber)).thenReturn(Optional.of(orderWithId));


        when(clientService.findById(Integer.valueOf(clientId))).thenReturn(clientTest);


        Model model = mock(Model.class);
        String viewName = clientController.deleteOrder(clientId, orderNumber, model);

        verify(orderItemJpaRepository, times(1)).deleteOrderItemsByOrderId(orderWithId.getOrderId());
        verify(orderService, times(1)).deleteOrder(orderWithId);


        verify(model, times(1)).addAttribute("clientName", clientTest.getName());
        verify(model, times(1)).addAttribute("clientId", clientId);

        assertEquals("order_cancel", viewName);
    }

    @Test
    public void testShowForm() throws Exception {
        when(orderService.findByClientId(anyInt())).thenReturn(Collections.emptyList());


        mockMvc.perform(get("/client/1/addOrder"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("client_restaurant_select"))
                .andExpect(model().attribute("clientId", 1));
    }

    @Test
    public void testShowFormOfAdd() throws Exception {
        String clientId = "1";
        this.mockMvc.perform(get("/client/" + clientId + "/addOrder"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("clientId", clientId))
                .andExpect(MockMvcResultMatchers.view().name("client_restaurant_select"));
    }

    @Test
    public void testShowRestaurantsByStreet() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/client/{clientId}/addOrder/restaurants", "1")
                        .param("street", "Example Street")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("client_found_restaurant"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("restaurants"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("clientId"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("street"));
    }

    @Test
    public void testShowOrderDetails() throws Exception {

        Client client = Client.builder().build();

        Client clientTest = client.withClientId(1).withName("John");

        Order order = Order.builder().build();
        Order orderTest = order.withOrderId(1).withOrderNumber("123456").withTotalPrice(BigDecimal.valueOf(50.00));

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderNumber("123456");
        orderDTO.setTotalPrice(BigDecimal.valueOf(50.00));

        when(clientService.findById(anyInt())).thenReturn(clientTest);
        when(orderService.findByOrderNumber(anyString())).thenReturn(Optional.of(orderTest));

        mockMvc.perform(get("/client/1/order/123456/orderDetails"))
                .andExpect(status().isOk())
                .andExpect(view().name("order_details"));
    }


}