package pl.example.business.dao;

import pl.example.domain.OrderItem;

public interface OrderItemDAO {

    OrderItem saveOrderItem(OrderItem orderItem);


    void deleteOrderItemsByOrderId(Integer orderId);
}
