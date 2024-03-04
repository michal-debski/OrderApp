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
    public void saveOrder(Order order) {

        OrderEntity orderEntity = orderEntityMapper.mapToEntity(order);
        orderJpaRepository.save(orderEntity);

    }

    @Override
    public void deleteOrder(Order order) {
        OrderEntity orderEntity = orderEntityMapper.mapToEntity(order);
        orderJpaRepository.delete(orderEntity);
    }

    @Override
    public Optional<Order> findByOrderNumber(String orderNumber) {
        return orderJpaRepository.findByOrderNumber(orderNumber)
                .map(orderEntityMapper::mapFromEntity);
    }

    @Override
    public List<Order> findByClientId(Integer id) {
        return orderJpaRepository.findByClientId(id).stream()
                .map(orderEntityMapper::mapFromEntity).toList();
    }

    @Override
    public List<Order> findByRestaurantId(Integer id) {
        return orderJpaRepository.findByRestaurantId(id).stream()
                .map(orderEntityMapper::mapFromEntity).toList();
    }


}
