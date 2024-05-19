package pl.example.infrastructure.database.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.example.domain.Order;
import pl.example.infrastructure.database.entity.OrderEntity;
import pl.example.infrastructure.database.repository.jpa.OrderJpaRepository;
import pl.example.infrastructure.database.repository.mapper.OrderEntityMapper;
import pl.example.util.DomainInput;
import pl.example.util.EntityInput;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderRepositoryTest {

    @Mock
    private OrderEntityMapper orderEntityMapper;
    @Mock
    private OrderJpaRepository orderJpaRepository;
    @InjectMocks
    private OrderRepository orderRepository;

    private OrderEntity orderEntity;
    private OrderEntity orderEntity1;
    private Order order;
    private Order order1;

    @BeforeEach
    public void setUp() {
        orderEntity = EntityInput.kindOfOrderEntity();
        orderEntity1 = EntityInput.kindOfOrderEntity1();
        order = DomainInput.kindOfOrder();
        order1 = DomainInput.kindOfOrder1();
//        restaurantEntity1 = EntityInput.kindOfRestaurantEntity1();
//        restaurantEntity2 = EntityInput.kindOfRestaurantEntity2();
//        restaurantOwnerEntity = EntityInput.kindOfRestaurantOwnerEntity();
//        addressEntity = EntityInput.kindOfRestaurantEntity().getAddress();
//        restaurant = DomainInput.kindOfRestaurant();
//        restaurant1 = DomainInput.kindOfRestaurant1();
//        restaurant2 = DomainInput.kindOfRestaurant2();
//        restaurantOwner = DomainInput.kindOfRestaurantOwner();
//        address = DomainInput.kindOfRestaurant().getAddress();

    }

    @Test
    void shouldSaveOrder() {

        when(orderEntityMapper.mapToEntity(order)).thenReturn(orderEntity);
        when(orderJpaRepository.save(orderEntity)).thenReturn(orderEntity);
        when(orderEntityMapper.mapFromEntity(orderEntity)).thenReturn(order);

        Order savedOrder = orderRepository.saveOrder(order);

        Assertions.assertEquals(order,savedOrder);
    }

    @Test
    void shouldDeleteOrder() {

        when(orderJpaRepository.findById(1)).thenReturn(Optional.of(orderEntity));

        orderRepository.deleteOrder(order);
        when(orderJpaRepository.findById(1)).thenReturn(Optional.empty());

        Assertions.assertFalse(orderJpaRepository.findById(1).isPresent());

    }

    @Test
    void shouldFindByOrderNumber() {

        when(orderJpaRepository.findByOrderNumber("123")).thenReturn(Optional.of(orderEntity));
        when(orderEntityMapper.mapFromEntity(orderEntity)).thenReturn(order);

        Optional<Order> byOrderNumber = orderRepository.findByOrderNumber("123");

        org.assertj.core.api.Assertions.assertThat(byOrderNumber).isPresent();
        Assertions.assertEquals(order,byOrderNumber.get());
    }

    @Test
    void shouldFindByClientId() {

        when(orderJpaRepository.findByClientId(4)).thenReturn(List.of(orderEntity));
        when(orderEntityMapper.mapFromEntity(orderEntity)).thenReturn(order);
        List<Order> orderList = orderRepository.findByClientId(4);

        Assertions.assertEquals(1, orderList.size());
    }

    @Test
    void shouldFindAll() {

        when(orderJpaRepository.findAll()).thenReturn(List.of(orderEntity,orderEntity1));
        when(orderEntityMapper.mapFromEntity(orderEntity)).thenReturn(order);
        when(orderEntityMapper.mapFromEntity(orderEntity1)).thenReturn(order1);
        List<Order> orderList = orderRepository.findAll();

        Assertions.assertEquals(2,orderList.size());
    }

    @Test
    void shouldUpdate() {
        when(orderEntityMapper.mapToEntity(order)).thenReturn(orderEntity);

        orderRepository.update(order);

        verify(orderJpaRepository, times(1)).save(orderEntity);
    }

    @Test
    void shouldGetTotalOrderPrice() {

        BigDecimal expectedTotalPrice = new BigDecimal("90.00");

        when(orderJpaRepository.getTotalOrderPrice(4)).thenReturn(expectedTotalPrice);

        BigDecimal totalOrderPrice = orderRepository.getTotalOrderPrice(4);

        Assertions.assertEquals(expectedTotalPrice,totalOrderPrice);


    }
}