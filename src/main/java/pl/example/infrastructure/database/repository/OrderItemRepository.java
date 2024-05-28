package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.OrderItemDAO;
import pl.example.domain.OrderItem;
import pl.example.infrastructure.database.entity.OrderItemEntity;
import pl.example.infrastructure.database.repository.jpa.OrderItemJpaRepository;
import pl.example.infrastructure.database.repository.mapper.OrderItemMapperEntity;

@Repository
@AllArgsConstructor
public class OrderItemRepository implements OrderItemDAO {
    private final OrderItemJpaRepository orderItemJpaRepository;
    private final OrderItemMapperEntity orderItemMapperEntity;

    @Override
    public OrderItem save(OrderItem orderItem) {
        OrderItemEntity orderEntity = orderItemMapperEntity.mapToEntity(orderItem);
        OrderItemEntity save = orderItemJpaRepository.save(orderEntity);
        return orderItemMapperEntity.mapFromEntity(save);
    }

    @Override
    public void deleteOrderItemsByOrderId(Integer orderId) {
        orderItemJpaRepository.deleteOrderItemsByOrderId(orderId);
    }
}
