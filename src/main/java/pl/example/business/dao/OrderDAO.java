package pl.example.business.dao;

import pl.example.domain.Order;
import pl.example.infrastructure.database.entity.OrderEntity;

import java.util.Optional;

public interface OrderDAO {

    Order saveOrder(Order order);

    void deleteOrder(OrderEntity entity);

    Optional<Order> findByOrderNumber(String orderNumber);
}
