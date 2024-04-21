package pl.example.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.example.api.controller.exception.NotFoundException;
import pl.example.business.dao.OrderDAO;
import pl.example.domain.Client;
import pl.example.domain.Order;
import pl.example.domain.OrderItem;
import pl.example.infrastructure.database.entity.ClientEntity;
import pl.example.infrastructure.database.entity.OrderEntity;
import pl.example.infrastructure.database.entity.OrderItemEntity;
import pl.example.infrastructure.database.entity.RestaurantEntity;
import pl.example.infrastructure.database.repository.jpa.OrderJpaRepository;
import pl.example.infrastructure.database.repository.jpa.RestaurantJpaRepository;
import pl.example.infrastructure.database.repository.mapper.ClientEntityMapper;
import pl.example.infrastructure.database.repository.mapper.OrderEntityMapper;
import pl.example.infrastructure.database.repository.mapper.OrderItemMapperEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class OrderRepository implements OrderDAO {

    private final OrderEntityMapper orderEntityMapper;
    private final OrderItemMapperEntity orderItemMapper;
    private final ClientEntityMapper clientEntityMapper;

    private final RestaurantJpaRepository restaurantJpaRepository;
    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order saveOrder(Order order, String restaurantName, Set<OrderItem> orderItems) {

        OrderEntity orderEntity = orderEntityMapper.mapToEntity(order);
        RestaurantEntity restaurant = restaurantJpaRepository.findByName(restaurantName)
                .orElseThrow(() -> new NotFoundException("Cannot find restaurant with name: [%s]"
                        .formatted(restaurantName)));
        orderEntity.setRestaurant(restaurant);
        Set<OrderItemEntity> orderItemEntities = orderItems.stream()
                .map(orderItemMapper::mapToEntity)
                .collect(Collectors.toSet());
        orderItemEntities.forEach(orderItem -> orderItem.setOrder(orderEntity));
        orderEntity.setOrderItems(orderItemEntities);
        orderEntity.setTotalPrice(order.getTotalPrice());
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
    public BigDecimal getTotalOrderPrice(Integer id) {
        return orderJpaRepository.getTotalOrderPrice(id);
    }


}
