package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.OrderDAO;
import pl.example.domain.Order;
import pl.example.infrastructure.database.entity.OrderEntity;
import pl.example.infrastructure.database.repository.jpa.OrderJpaRepository;
import pl.example.infrastructure.database.repository.mapper.OrderEntityMapper;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class OrderRepository implements OrderDAO {

    private final OrderEntityMapper orderEntityMapper;
    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order saveOrder(Order order) {
        OrderEntity toSave = orderEntityMapper.mapToEntity(order);
        OrderEntity saved = orderJpaRepository.save(toSave);
        return orderEntityMapper.mapFromEntity(saved);
    }

    @Override
    public void deleteOrder(OrderEntity order) {
        orderJpaRepository.delete(order);
    }

    @Override
    public Optional<Order> findByOrderNumber(String orderNumber) {
        return orderJpaRepository.findByOrderNumber(orderNumber)
                .map(orderEntityMapper::mapFromEntity);
    }

    @Override
    public List<Order> findByClientId(Integer id) {
        return orderJpaRepository.findByClientId(id)
                .stream()
                .map(orderEntityMapper::mapFromEntity)
                .toList();
    }
}
