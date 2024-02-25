package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.OrderItemDAO;
import pl.example.domain.OrderItem;
import pl.example.infrastructure.database.entity.OrderItemEntity;
import pl.example.infrastructure.database.repository.jpa.OrderItemJpaRepository;
import pl.example.infrastructure.database.repository.mapper.OrderItemEntityMapper;

@Repository
@AllArgsConstructor
public class OrderItemRepository implements OrderItemDAO {

    private final OrderItemJpaRepository orderItemJpaRepository;
    private final OrderItemEntityMapper orderItemEntityMapper;

    @Override
    public OrderItem saveOrderItem(OrderItem orderItem) {
        OrderItemEntity toSave = orderItemEntityMapper.mapToEntity(orderItem);
        OrderItemEntity saved = orderItemJpaRepository.save(toSave);
        return orderItemEntityMapper.mapFromEntity(saved);

    }

    @Override
    public void deleteOrderItem(Integer orderItemId) {
        orderItemJpaRepository.deleteById(orderItemId);
    }

    @Override
    public OrderItem findOrderItemById(Integer orderItemId) {
        OrderItemEntity entity = orderItemJpaRepository.findById(orderItemId).get();
        return orderItemEntityMapper.mapFromEntity(entity);
    }

}
