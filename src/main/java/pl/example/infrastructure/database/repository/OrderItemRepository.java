package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.OrderItemDAO;
import pl.example.domain.OrderItem;
import pl.example.infrastructure.database.repository.jpa.OrderItemJpaRepository;
import pl.example.infrastructure.database.repository.mapper.OrderItemMapperEntity;

@Repository
@AllArgsConstructor
public class OrderItemRepository implements OrderItemDAO {
    private final OrderItemJpaRepository orderItemJpaRepository;
    private final OrderItemMapperEntity orderItemMapperEntity;

    @Override
    public void save(OrderItem orderItem) {
        orderItemJpaRepository.save(orderItemMapperEntity.mapToEntity(orderItem));
    }

    @Override
    public void delete(OrderItem orderItem) {
        orderItemJpaRepository.delete(orderItemMapperEntity.mapToEntity(orderItem));

    }

    @Override
    public OrderItem findByOrderId(Integer id) {
        return orderItemJpaRepository.findById(id).map(orderItemMapperEntity::mapFromEntity).orElseThrow();
    }

    @Override
    public void deleteOrderItemsByOrderId(Integer orderId) {
        orderItemJpaRepository.deleteOrderItemsByOrderId(orderId);
    }
}
