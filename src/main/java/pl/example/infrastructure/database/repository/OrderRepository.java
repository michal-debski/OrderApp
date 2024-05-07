package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.example.business.dao.OrderDAO;
import pl.example.domain.Client;
import pl.example.domain.Order;
import pl.example.infrastructure.database.entity.ClientEntity;
import pl.example.infrastructure.database.entity.OrderEntity;
import pl.example.infrastructure.database.repository.jpa.OrderJpaRepository;
import pl.example.infrastructure.database.repository.mapper.ClientEntityMapper;
import pl.example.infrastructure.database.repository.mapper.OrderEntityMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class OrderRepository implements OrderDAO {

    private final OrderEntityMapper orderEntityMapper;
    private final ClientEntityMapper clientEntityMapper;
    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order saveOrder(Order order) {
        OrderEntity orderEntity = orderEntityMapper.mapToEntity(order);
        OrderEntity save = orderJpaRepository.save(orderEntity);
        return orderEntityMapper.mapFromEntity(save);
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
    public List<Order> findByClient(Client client) {
        ClientEntity clientEntity = clientEntityMapper.mapToEntity(client);
        List<OrderEntity> byClient = orderJpaRepository.findByClient(clientEntity);
        return byClient.stream().map(orderEntityMapper::mapFromEntity).toList();
    }

    @Override
    public List<Order> findByRestaurantId(Integer id) {
        return orderJpaRepository.findByRestaurantId(id).stream()
                .map(orderEntityMapper::mapFromEntity).toList();
    }

    @Override
    public Optional<Order> findById(Integer orderId) {
        return orderJpaRepository.findById(orderId)
                .map(orderEntityMapper::mapFromEntity);
    }

    @Override
    public List<Order> findAll() {
        List<OrderEntity> orderEntityList = orderJpaRepository.findAll();
        return orderEntityList.stream().map(orderEntityMapper::mapFromEntity).toList();
    }

    @Override
    public void update(Order order) {
        orderJpaRepository.save(orderEntityMapper.mapToEntity(order));
    }


    @Override
    public BigDecimal getTotalOrderPrice(Integer id) {
        return orderJpaRepository.getTotalOrderPrice(id);
    }


}
