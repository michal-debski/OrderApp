package pl.example.api.controller;

import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import pl.example.api.dto.OrderDTO;
import pl.example.api.dto.mapper.OrderMapper;
import pl.example.business.ClientService;
import pl.example.business.OrderService;
import pl.example.domain.Client;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static pl.example.util.DomainInput.kindOfClient;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = ClientHomeController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class ClientHomeControllerTest {
    @MockBean
    private OrderService orderService;

    @MockBean
    private ClientService clientService;

    @MockBean
    private OrderMapper orderMapper;

    @MockBean
    private Model model;

    @InjectMocks
    private ClientHomeController clientHomeController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        Client client = kindOfClient();
        when(clientService.findLoggedClient()).thenReturn(client);
    }

    @Test
    void testClientHomePage() throws Exception {
        mockMvc.perform(get("/client"))
                .andExpect(status().isOk())
                .andExpect(view().name("client_homepage"));
    }

    @Test
    void testShowAllOrdersModel() {
        Integer clientId = 1;
        when(orderService.findByClientId(clientId)).thenReturn(new ArrayList<>());
        when(orderMapper.mapToDTO(any())).thenReturn(new OrderDTO());

        String viewName = clientHomeController.showAllOrders(model);
        Assertions.assertThat(viewName).isEqualTo("client_orders");
    }
}