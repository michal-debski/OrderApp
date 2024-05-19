package pl.example.infrastructure.database.repository.jpa;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import pl.example.infrastructure.database.entity.OrderEntity;
import pl.example.util.EntityInput;
import pl.example.util.integration.configuration.PersistenceContainerTestConfiguration;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yaml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(PersistenceContainerTestConfiguration.class)
class OrderJpaRepositoryTest {

    private OrderJpaRepository orderJpaRepository;

    @Test
    void shouldSaveAndFindByOrderNumber() {

        //given
        OrderEntity orderEntity = EntityInput.kindOfOrderEntity();

        //when
        orderJpaRepository.save(orderEntity);
        Optional<OrderEntity> order = orderJpaRepository.findByOrderNumber(orderEntity.getOrderNumber());
        //then
        Assertions.assertEquals(order, orderJpaRepository.findByOrderNumber("123"));
    }

    @Test
    void shouldSaveAndFindAll() {
        //given
        OrderEntity orderEntity1 = EntityInput.kindOfOrderEntity();
        OrderEntity orderEntity2 = EntityInput.kindOfOrderEntity1();


        //when
        orderJpaRepository.save(orderEntity1);
        orderJpaRepository.save(orderEntity2);
        List<OrderEntity> orders = orderJpaRepository.findAll();
        //then
        Assertions.assertEquals(2, orders.size());
    }

    @Test
    void shouldSaveAndFindByClientId() {
        //given
        OrderEntity orderEntity1 = EntityInput.kindOfOrderEntity();

        //when
        orderJpaRepository.save(orderEntity1);
        List<OrderEntity> ordersByClientId = orderJpaRepository.findByClientId(orderEntity1.getClient().getClientId());
        //then
        Assertions.assertEquals(1, ordersByClientId.size());
    }

    @Test
    void shouldGetTotalOrderPrice() {
        //given
        OrderEntity orderEntity1 = EntityInput.kindOfOrderEntity();
        //when
        orderJpaRepository.save(orderEntity1);
        //then
        BigDecimal totalOrderPrice = orderJpaRepository.getTotalOrderPrice(orderEntity1.getOrderId());
        Assertions.assertNull(totalOrderPrice);


    }
}