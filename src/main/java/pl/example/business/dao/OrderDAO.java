package pl.example.business.dao;

import pl.example.domain.Order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderDAO {

    Order saveOrder(Order order);

    void deleteOrder(Order entity);

    Optional<Order> findByOrderNumber(String orderNumber);

    List<Order> findByClientId(Integer id);


    BigDecimal getTotalOrderPrice(Integer id);

    List<Order> findAll();

    void update(Order order);
}
