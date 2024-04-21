package pl.example.business.dao;

import pl.example.domain.Client;
import pl.example.domain.Order;
import pl.example.domain.OrderItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface OrderDAO {

    Order saveOrder(Order order, String name, Set<OrderItem> orderItems);

    void deleteOrder(Order entity);

    Optional<Order> findByOrderNumber(String orderNumber);

    List<Order> findByClientId(Integer id);
    List<Order> findByClient(Client client);

    List<Order> findByRestaurantId(Integer id);

    Optional<Order> findById(Integer orderId);
     BigDecimal getTotalOrderPrice(Integer id);
}
