package pl.example.api.controller;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.example.api.dto.mapper.OrderMapper;
import pl.example.business.OrderService;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = RestaurantOwnerOrderController.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class RestaurantOwnerOrderControllerTest {

    @MockBean
    private OrderService orderService;
    @MockBean
    private OrderMapper orderMapper;
    @InjectMocks
    private RestaurantOwnerOrderController restaurantOwnerOrderController;

    @Test
    void showOrdersForRestaurant() {
    }

    @Test
    void updateMealForm() {
    }

    @Test
    void updateOrderStatus() {
    }
}