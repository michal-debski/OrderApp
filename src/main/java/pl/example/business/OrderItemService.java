package pl.example.business;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.example.business.dao.OrderItemDAO;
import pl.example.domain.OrderItem;
import pl.example.infrastructure.database.repository.OrderItemRepository;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemDAO orderItemDAO;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemDAO.saveOrderItem(orderItem);
    }

    @Transactional
    public void deleteOrderItem(int orderItemId) {
        orderItemDAO.deleteOrderItem(orderItemId);
    }

    @Transactional
    public OrderItem findOrderItemById(Integer orderItemId) {
        return orderItemDAO.findOrderItemById(orderItemId);
    }

    @Transactional
    public void increaseItemQuantity(int orderItemId) {
        OrderItem orderItem = orderItemRepository.findOrderItemById(orderItemId);
        int currentQuantity = orderItem.getQuantity();
        OrderItem updatedOrderItem = OrderItem.builder()
                .quantity(currentQuantity + 1)
                .build();
        orderItemRepository.saveOrderItem(updatedOrderItem);
    }

}
