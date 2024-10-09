package pl.example.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.OrderDAO;
import pl.example.domain.Order;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderDAO orderDAO;

    private final ClientService clientService;

    private final RestaurantService restaurantService;

    @Transactional
    public void deleteOrder(Order order) {
        orderDAO.deleteOrder(order);
    }

    @Transactional
    public Order saveOrder(Order order) {
        return orderDAO.saveOrder(order);
    }


    public Optional<Order> findByOrderNumber(String orderNumber) {
        return orderDAO.findByOrderNumber(orderNumber);
    }


    @Transactional
    public List<Order> findOrderByClientId(Integer clientId) {
        return orderDAO.findByClientId(clientId);
    }

    @Transactional
    public Order buildOrder(Integer restaurantId) {
        OffsetDateTime dateOfOrder = OffsetDateTime.now();
        Order orderPlaced = Order.builder()
                .orderNumber(generateOrderNumber())
                .status("Preparing by chef")
                .orderDate(dateOfOrder)
                .client(clientService.findLoggedClient())
                .restaurant(restaurantService.findRestaurantById(restaurantId))
                .build();
        return orderDAO.saveOrder(orderPlaced);
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

    public BigDecimal getTotalOrderPrice(Integer id) {
        return orderDAO.getTotalOrderPrice(id);
    }

    @SuppressWarnings("SameParameterValue")
    private int randomInt(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }

    @SuppressWarnings("SameParameterValue")
    private int randomChar(char min, char max) {
        return (char) new Random().nextInt(max - min) + min;
    }

    public List<Order> findAllOrders() {
        return orderDAO.findAll();
    }

    @Transactional
    public void updateOrder(Order order) {
        orderDAO.update(order);
    }
}
