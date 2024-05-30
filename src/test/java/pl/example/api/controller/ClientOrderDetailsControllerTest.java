package pl.example.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.example.api.dto.OrderDTO;
import pl.example.api.dto.mapper.OrderMapper;
import pl.example.business.OrderService;
import pl.example.domain.Order;

import static org.mockito.Mockito.when;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = ClientOrderDetailsController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class ClientOrderDetailsControllerTest {

    private MockMvc mockMvc;
    @MockBean
    private OrderService orderService;
    @MockBean
    private OrderMapper orderMapper;

    @Test
    void testShowOrderDetails() throws Exception {
        Order order = Order.builder().build();
        order.setOrderNumber("123456");
        OrderDTO orderDTO = new OrderDTO();
        when(orderService.findByOrderNumber("123456")).thenReturn(java.util.Optional.of(order));
        when(orderMapper.mapToDTO(order)).thenReturn(orderDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/client/order/123456/orderDetails"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("order_details"))
                .andExpect(MockMvcResultMatchers.model().attribute("order", orderDTO));
    }

}