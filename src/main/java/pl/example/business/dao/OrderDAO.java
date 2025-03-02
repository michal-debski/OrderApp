package pl.example.business.dao;

import pl.example.domain.Order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderDAO {

    Order saveOrder(Order order);

    void deleteOrder(Order entity);

    Optional<Order> findOrderByOrderNumber(String orderNumber);

    List<Order> findOrderByClientId(String email);


    BigDecimal getTotalOrderPrice(Integer id);

    List<Order> findAll();

    void updateOrder(Order order);
}
