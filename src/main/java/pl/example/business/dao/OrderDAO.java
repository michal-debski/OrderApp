package pl.example.business.dao;

import pl.example.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDAO {

    void saveOrder(Order order);

    void deleteOrder(Order entity);

    Optional<Order> findByOrderNumber(String orderNumber);

    List<Order> findByClientId(Integer id);

    List<Order> findByRestaurantId(Integer id);

    Optional<Order> findById(Integer orderId);
}
