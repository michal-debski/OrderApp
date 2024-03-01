package pl.example.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.OrderDAO;
import pl.example.domain.Order;
import pl.example.infrastructure.database.entity.OrderEntity;
import pl.example.infrastructure.database.repository.mapper.OrderEntityMapper;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderDAO orderDAO;
    private final OrderEntityMapper orderEntityMapper;


    @Transactional
    public void deleteOrder(OrderEntity order) {
        orderDAO.deleteOrder(order);
    }

    @Transactional
    public Order createOrder(Order order) {
        OffsetDateTime when = OffsetDateTime.now();
        return orderDAO.saveOrder(
                Order.builder()
                        .orderNumber(order.getOrderNumber())
                        .client(order.getClient())
                        .restaurant(order.getRestaurant())
                        .totalPrice(order.getTotalPrice())
                        .status("We are working on your meal!")
                        .orderDate(when)
                        .deliveryDate(when.plusHours(1))
                        .build()
        );
    }

    @Transactional
    public Optional<Order> findByOrderNumber(String orderNumber) {
        return orderDAO.findByOrderNumber(orderNumber);
    }

    private String generateOrderNumber(OffsetDateTime when) {
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

    @Transactional
    public List<Order> findByClientId(Integer clientId) {
        return orderDAO.findByClientId(clientId);
    }
}
