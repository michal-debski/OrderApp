package pl.example.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.OrderDAO;
import pl.example.domain.Order;
import pl.example.domain.OrderItem;
import pl.example.infrastructure.database.repository.jpa.OrderJpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderDAO orderDAO;
    private final OrderJpaRepository orderJpaRepository;


    @Transactional
    public void deleteOrder(Order order) {
        orderDAO.deleteOrder(order);
    }

    @Transactional
    public void save(Order order) {
        orderDAO.saveOrder(order);
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
    @Transactional
    public void updateOrder(Integer orderId) {
        Order order = orderDAO.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found."));

        BigDecimal totalPrice = calculateTotalPrice(order.withOrderId(orderId));
        Order withTotalPrice = order.withTotalPrice(totalPrice);

        orderDAO.saveOrder(withTotalPrice);
    }

    private BigDecimal calculateTotalPrice(Order order) {
       return orderJpaRepository.getTotalOrderPrice(order.getOrderId());
    }
}
