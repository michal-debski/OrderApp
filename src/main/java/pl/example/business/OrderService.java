package pl.example.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.OrderDAO;
import pl.example.domain.Client;
import pl.example.domain.Order;
import pl.example.domain.OrderItem;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderDAO orderDAO;

    private final ClientService clientService;

    @Transactional
    public void deleteOrder(Order order) {
        orderDAO.deleteOrder(order);
    }

    @Transactional
    public Order save(Order order, String name, Set<OrderItem> orderItems) {
        return orderDAO.saveOrder(order, name, orderItems);
    }


    public Optional<Order> findByOrderNumber(String orderNumber) {
        return orderDAO.findByOrderNumber(orderNumber);
    }


    public List<Order> findByRestaurantId(Integer id) {
        return orderDAO.findByRestaurantId(id);
    }


    @Transactional
    public List<Order> findByClientId(Integer clientId) {
        return orderDAO.findByClientId(clientId);
    }

    public List<Order> findByClient(Client client) {
        return orderDAO.findByClient(client);
    }

    //    @Transactional
//    public void updateOrder(Integer orderId) {
//        Order order = orderDAO.findById(orderId)
//                .orElseThrow(() -> new IllegalArgumentException("Order not found."));
//
//        BigDecimal totalPrice = calculateTotalPrice(order.withOrderId(orderId));
//        Order withTotalPrice = order.withTotalPrice(totalPrice);
//
//        orderDAO.saveOrder(withTotalPrice);
//    }
    @Transactional
    public Order buildOrder(Set<OrderItem> orderItems, String name) {
        Order orderPlaced = buildOrder(orderItems);
        Order order = orderDAO.saveOrder(orderPlaced, name, orderItems);
        return order;
    }

    private Order buildOrder(Set<OrderItem> orderItems) {
        OffsetDateTime dateOfOrder = OffsetDateTime.now();
        return Order.builder()
                .orderNumber(generateOrderNumber())
                .status("Preparing by chef")
                .orderDate(dateOfOrder)
                .client(clientService.findLoggedClient())
                .orderItems(orderItems)
                .totalPrice(calculateTotalPrice(orderItems))
                .build();
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

    private BigDecimal calculateTotalPrice(Set<OrderItem> orderItems) {
        return orderItems.stream()
                .map(this::calculateOrderValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateOrderValue(OrderItem orderItem) {
        orderItem.getMeal().getPrice();
        return orderItem.getMeal().getPrice().multiply(new BigDecimal(orderItem.getQuantity()));
    }
}
