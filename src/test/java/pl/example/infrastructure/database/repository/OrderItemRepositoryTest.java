package pl.example.infrastructure.database.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.example.domain.OrderItem;
import pl.example.infrastructure.database.entity.OrderItemEntity;
import pl.example.infrastructure.database.repository.jpa.OrderItemJpaRepository;
import pl.example.infrastructure.database.repository.mapper.OrderItemMapperEntity;
import pl.example.util.DomainInput;
import pl.example.util.EntityInput;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderItemRepositoryTest {

    @Mock
    private OrderItemJpaRepository orderItemJpaRepository;
    @Mock
    private OrderItemMapperEntity orderItemMapperEntity;

    @InjectMocks
    private OrderItemRepository orderItemRepository;
    private OrderItem orderItem;
    private OrderItem secondOrderItem;
    private OrderItemEntity orderItemEntity;
    private OrderItemEntity secondOrderItemEntity;

    @BeforeEach
    public void setUp() {
        orderItemEntity = EntityInput.kindOfOrderItemEntity();
        secondOrderItemEntity = EntityInput.kindOfOrderItemEntity();
        orderItem = DomainInput.kindOfOrderItem();
        secondOrderItem = DomainInput.kindOfOrderItem1();
    }


    @Test
    void shouldSave() {
        when(orderItemMapperEntity.mapToEntity(orderItem)).thenReturn(orderItemEntity);
        when(orderItemJpaRepository.save(orderItemEntity)).thenReturn(orderItemEntity);
        when(orderItemMapperEntity.mapFromEntity(orderItemEntity)).thenReturn(orderItem);

        OrderItem savedOrder = orderItemRepository.save(orderItem);

        Assertions.assertEquals(orderItem, savedOrder);
    }


    @Test
    void shouldDeleteOrderItemsByOrderId() {
        Integer orderId = 1;

        // When
        orderItemRepository.deleteOrderItemsByOrderId(orderId);

        // Then
        verify(orderItemJpaRepository).deleteOrderItemsByOrderId(orderId);
    }
}