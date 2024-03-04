package pl.example.business.dao;

import pl.example.domain.OrderItem;

public interface OrderItemDAO {

    void save(OrderItem orderItem);

    void delete(OrderItem orderItems);

    OrderItem findByOrderId(Integer id);

    void deleteOrderItemsByOrderId(Integer orderId);
}
